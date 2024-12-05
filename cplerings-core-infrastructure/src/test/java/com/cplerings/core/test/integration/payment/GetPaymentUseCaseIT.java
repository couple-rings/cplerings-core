package com.cplerings.core.test.integration.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.payment.response.GetPaymentResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.payment.APayment;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class GetPaymentUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenCustomer_whenViewPayment() {
        Payment payment = Payment.builder()
                .status(PaymentStatus.PENDING)
                .type(PaymentType.VNPAY)
                .description("test")
                .secureHash("DummyHash")
                .amount(Money.create(BigDecimal.valueOf(100)))
                .id(Long.valueOf(1))
                .paymentReceiverType(PaymentReceiverType.CRAFT_STAGE)
                .build();
        testDataSource.save(payment);
        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.PAYMENT_PATH, 1L)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnPayment(response);

    }

    private void thenResponseReturnPayment(WebTestClient.ResponseSpec response) {
        final GetPaymentResponse responseBody = response.expectBody(GetPaymentResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final APayment payment = responseBody.getData();
        assertThat(payment).isNotNull();
        assertThat(payment.getDescription()).isNotNull();
    }
}
