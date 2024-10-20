package com.cplerings.core.test.component.payment;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.application.shared.service.payment.PaymentRequestService;
import com.cplerings.core.common.payment.PaymentConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.service.payment.PaymentInfoImpl;
import com.cplerings.core.infrastructure.service.payment.PaymentRequestImpl;
import com.cplerings.core.test.shared.AbstractCT;
import com.cplerings.core.test.shared.account.AccountTestConstant;

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

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void givenPaymentService_whenGeneratePaymentRequest() {
        final Account account = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(account).isNotNull();
        final PaymentRequestImpl paymentRequest = (PaymentRequestImpl) paymentRequestService.requestPayment(PaymentInfoImpl.builder()
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
                    .doesNotContainPattern("Error.html\\?code=\\d\\d");
        });
    }
}
