package com.cplerings.core.test.integration.payment;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.payment.request.VNPayPaymentRequest;
import com.cplerings.core.application.payment.datasource.ProcessVNPayPaymentDataSource;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.payment.VNPayUtils;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.PaymentRepository;
import com.cplerings.core.test.shared.AbstractIT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.Objects;

class ProcessVNPayPaymentUseCaseIT extends AbstractIT {

    private static final String PAYMENT_FOLDER = "data/integration/payment";
    private static final String VNPAY_WEBHOOK_RESULT = "/vnpay-webhook-result.json";

    @Autowired
    private ProcessVNPayPaymentDataSource processVNPayPaymentDataSource;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void givenVNPay_whenReturnPaymentResultThroughWebhook() {
        final VNPayPaymentRequest request = getTestDataLoader(PAYMENT_FOLDER).loadAsObject(VNPAY_WEBHOOK_RESULT, VNPayPaymentRequest.class);
        Payment payment = Payment.builder()
                .status(PaymentStatus.PENDING)
                .type(PaymentType.VNPAY)
                .description(request.getVnp_OrderInfo())
                .secureHash("DummyHash")
                .amount(Money.create(BigDecimal.valueOf(request.getVnp_Amount()))
                        .divide(BigDecimal.valueOf(100)))
                .id(Long.valueOf(request.getVnp_TxnRef()))
                .build();
        payment = processVNPayPaymentDataSource.save(payment);

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VNPAY_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        payment = thenPaymentIsUpdatedToSuccessful(response, payment.getId());
        thenVNPayTransactionIsCreated(payment, request);
    }

    private Payment thenPaymentIsUpdatedToSuccessful(WebTestClient.ResponseSpec response, Long id) {
        final Payment payment = processVNPayPaymentDataSource.findPaymentByIdWithVNPayTransaction(id)
                .orElse(null);
        assertThat(payment).isNotNull();

        assertThat(payment.getStatus()).isEqualTo(PaymentStatus.SUCCESSFUL);
        return payment;
    }

    private void thenVNPayTransactionIsCreated(Payment payment, VNPayPaymentRequest request) {
        final VNPayTransaction vnPayTransaction = payment.getVnPayTransaction();
        assertThat(vnPayTransaction).isNotNull();
        assertThat(vnPayTransaction.getTransactionId()).isEqualTo(Objects.toString(request.getVnp_TransactionNo()));
        assertThat(vnPayTransaction.getAmount()).isEqualTo(Money.create(BigDecimal.valueOf(request.getVnp_Amount()))
                .divide(BigDecimal.valueOf(100)));
        assertThat(vnPayTransaction.getPayDate()).isEqualTo(VNPayUtils.toInstant(request.getVnp_PayDate()));
        assertThat(vnPayTransaction.getBankCode()).isEqualTo(request.getVnp_BankCode());
        assertThat(vnPayTransaction.getBankTransferId()).isEqualTo(request.getVnp_BankTranNo());
        assertThat(vnPayTransaction.getCardType()).isEqualTo(request.getVnp_CardType());
        assertThat(vnPayTransaction.getOrderInfo()).isEqualTo(request.getVnp_OrderInfo());
        assertThat(vnPayTransaction.getSecureHash()).isEqualTo(request.getVnp_SecureHash());
    }
}
