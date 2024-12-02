package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.payment.request.VNPayPaymentRequest;
import com.cplerings.core.application.payment.input.VNPayPaymentInput;
import com.cplerings.core.application.shared.service.payment.PaymentVerificationService;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.payment.DesignSessionPayment;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DesignSessionRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.TestDataLoader;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProcessDesignSessionPaymentUseCaseIT extends AbstractIT {

    private static final String PAYMENT_FOLDER = "data/integration/payment";
    private static final String VNPAY_WEBHOOK_RESULT = "/vnpay-webhook-result.json";

    @MockBean
    private PaymentVerificationService<VNPayPaymentInput> paymentVerificationService;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DesignSessionRepository designSessionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private VNPayPaymentRequest request;

    @BeforeEach
    public void loadPaymentResult() {
        when(paymentVerificationService.paymentIsValid(any(VNPayPaymentInput.class))).thenReturn(true);

        final TestDataLoader testDataLoader = TestDataLoader.builder()
                .folder(PAYMENT_FOLDER)
                .objectMapper(objectMapper)
                .build();
        this.request = testDataLoader.loadAsObject(VNPAY_WEBHOOK_RESULT, VNPayPaymentRequest.class);

        Payment payment = Payment.builder()
                .type(PaymentType.VNPAY)
                .amount(Money.create(BigDecimal.valueOf(request.getVnp_Amount())))
                .status(PaymentStatus.PENDING)
                .secureHash(request.getVnp_SecureHash())
                .description(request.getVnp_OrderInfo())
                .paymentReceiverType(PaymentReceiverType.DESIGN_FEE)
                .build();
        payment = testDataSource.save(payment);

        Account account = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(account).isNotNull();

        final DesignSessionPayment designSessionPayment = DesignSessionPayment.builder()
                .customer(account)
                .payment(payment)
                .designSessionId(UUID.randomUUID())
                .build();
        testDataSource.save(designSessionPayment);
    }

    @Test
    void givenPayment_whenProcessDesignSessionPayment() {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VNPAY_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenDesignSessionsAreCreated();
    }

    private void thenDesignSessionsAreCreated() {
        final Collection<DesignSession> designSessions = designSessionRepository.findAllByCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);
        assertThat(designSessions).hasSize(3);
    }
}
