package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.order.data.StandardOrderPaymentLinkData;
import com.cplerings.core.api.order.request.PayStandardOrderRequest;
import com.cplerings.core.api.order.response.PayStandardOrderResponse;
import com.cplerings.core.api.payment.request.VNPayPaymentRequest;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.payment.datasource.ProcessVNPayPaymentDataSource;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.StandardOrderTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

class PayStandardOrderUseCaseIT extends AbstractIT {

    private static final String PAYMENT_FOLDER = "data/integration/payment";
    private static final String VNPAY_WEBHOOK_RESULT = "/vnpay-webhook-result.json";

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private StandardOrderTestHelper standardOrderTestHelper;

    @Autowired
    private ProcessVNPayPaymentDataSource processVNPayPaymentDataSource;

    @Autowired
    private TestDataSource testDataSource;

    private StandardOrder standardOrder;

    @BeforeEach
    void setUpCustomOrderAndCraftingStages() {
        StandardOrder standardOrder = standardOrderTestHelper.createStandardOrderWithJewelries();

        final VNPayPaymentRequest request = getTestDataLoader(PAYMENT_FOLDER).loadAsObject(VNPAY_WEBHOOK_RESULT, VNPayPaymentRequest.class);
        Payment payment = Payment.builder()
                .status(PaymentStatus.PENDING)
                .type(PaymentType.VNPAY)
                .description(request.getVnp_OrderInfo())
                .secureHash("DummyHash")
                .amount(Money.create(BigDecimal.valueOf(request.getVnp_Amount()))
                        .divide(BigDecimal.valueOf(100)))
                .id(Long.valueOf(request.getVnp_TxnRef()))
                .paymentReceiverType(PaymentReceiverType.STANDARD)
                .build();

        payment = processVNPayPaymentDataSource.save(payment);
        standardOrder.setPayment(payment);
        this.standardOrder = testDataSource.save(standardOrder);
    }

    @Test
    void givenCustomer_whenPayForStandardOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.PAY_STANDARD_ORDER_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(PayStandardOrderRequest.builder()
                        .standardOrderId(standardOrder.getId())
                        .build())
                .send();

        thenResponseIsOk(response);
        thenPaymentLinkExists(response);
    }

    private void thenPaymentLinkExists(WebTestClient.ResponseSpec response) {
        final PayStandardOrderResponse responseBody = response.expectBody(PayStandardOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(StandardOrderPaymentLinkData.class);
        assertThat(responseBody.getData().paymentLink()).isNotBlank();
        assertThat(responseBody.getData().paymentId()).isNotNull();
    }
}
