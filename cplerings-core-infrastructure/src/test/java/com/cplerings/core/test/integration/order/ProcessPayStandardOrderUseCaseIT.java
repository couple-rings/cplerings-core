package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.payment.request.VNPayPaymentRequest;
import com.cplerings.core.application.payment.input.VNPayPaymentInput;
import com.cplerings.core.application.shared.service.payment.PaymentVerificationService;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.TestDataLoader;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.order.StandardOrderTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProcessPayStandardOrderUseCaseIT extends AbstractIT {

    private static final String PAYMENT_FOLDER = "data/integration/payment";
    private static final String VNPAY_WEBHOOK_RESULT = "/vnpay-webhook-result.json";

    @MockBean
    private PaymentVerificationService<VNPayPaymentInput> paymentVerificationService;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StandardOrderTestHelper standardOrderTestHelper;

    @Test
    void givenPayment_whenProcessFirstCraftingStageDeposit() {
        StandardOrder standardOrder = standardOrderTestHelper.createStandardOrder();
        when(paymentVerificationService.paymentIsValid(any(VNPayPaymentInput.class))).thenReturn(true);
        final TestDataLoader testDataLoader = TestDataLoader.builder()
                .folder(PAYMENT_FOLDER)
                .objectMapper(objectMapper)
                .build();
        VNPayPaymentRequest request = testDataLoader.loadAsObject(VNPAY_WEBHOOK_RESULT, VNPayPaymentRequest.class);

        Payment paymentLocal = Payment.builder()
                .type(PaymentType.VNPAY)
                .amount(Money.create(BigDecimal.valueOf(request.getVnp_Amount())))
                .status(PaymentStatus.PENDING)
                .secureHash(request.getVnp_SecureHash())
                .description(request.getVnp_OrderInfo())
                .paymentReceiverType(PaymentReceiverType.STANDARD)
                .build();
        Payment payment = testDataSource.save(paymentLocal);
        standardOrder.setPayment(payment);
        standardOrder = testDataSource.save(standardOrder);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VNPAY_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenStandardOrderIsPaid(standardOrder.getId());
    }

    private void thenStandardOrderIsPaid(Long id) {
        final StandardOrder standardOrder = testDataSource.getStandardOrderById(id);
        assertThat(standardOrder.getStatus()).isEqualByComparingTo(StandardOrderStatus.PAID);
        assertThat(standardOrder.getStandardOrderItems()).allSatisfy(x -> {
            assertThat(x.getJewelry().getStatus()).isEqualByComparingTo(JewelryStatus.PURCHASED);
        });
    }
}
