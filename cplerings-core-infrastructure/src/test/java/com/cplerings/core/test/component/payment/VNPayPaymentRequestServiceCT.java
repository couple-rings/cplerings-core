package com.cplerings.core.test.component.payment;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.application.shared.service.payment.PaymentInfo;
import com.cplerings.core.application.shared.service.payment.PaymentRequest;
import com.cplerings.core.application.shared.service.payment.PaymentRequestService;
import com.cplerings.core.common.payment.PaymentConstant;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.test.shared.AbstractCT;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
class VNPayPaymentRequestServiceCT extends AbstractCT {

    @Autowired
    @Qualifier(PaymentConstant.VNPAY_PAYMENT_SERVICE_NAME)
    private PaymentRequestService paymentRequestService;

    @Test
    void givenPaymentService_whenGeneratePaymentRequest() {
        final PaymentRequest paymentRequest = paymentRequestService.requestPayment(PaymentInfo.builder()
                .amount(Money.create(BigDecimal.valueOf(123123)))
                .description("Payment for 123123")
                .build());
        final String paymentLink = URLDecoder.decode(paymentRequest.getPaymentLink(), StandardCharsets.US_ASCII);
        final WebTestClient.ResponseSpec response = WebTestClient.bindToServer()
                .baseUrl(paymentLink)
                .build()
                .get()
                .exchange();

        thenResponseIsRedirection(response);
        thenNewLocationIsNotErrorPage(response);
    }

    private void thenNewLocationIsNotErrorPage(WebTestClient.ResponseSpec response) {
        response.expectHeader().value("Location", location -> {
            log.info(location);
            assertThat(location).isNotNull()
                    .doesNotContainPattern("Error.html\\?code=((0[2-9])|([1-9][0-9]))");
        });
    }
}
