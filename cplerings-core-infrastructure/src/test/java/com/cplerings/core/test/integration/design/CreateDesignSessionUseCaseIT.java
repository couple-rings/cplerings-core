package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.design.data.DesignSessionPayment;
import com.cplerings.core.api.design.response.CreateDesignSessionResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.payment.PaymentReceiver;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.PaymentReceiverRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class CreateDesignSessionUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentReceiverRepository paymentReceiverRepository;

    @Test
    void givenStaff_whenCreateDesignSessionUseCase() {
        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CREATE_DESIGN_SESSION_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.POST)
                .send();

        thenResponseIsOk(response);
        thenCreateSuccessfullyAndReturnPaymentLink(response);
        thenPaymentReceiverIsCreated();
    }

    private void thenCreateSuccessfullyAndReturnPaymentLink(WebTestClient.ResponseSpec response) {
        final CreateDesignSessionResponse responseBody = response.expectBody(CreateDesignSessionResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(DesignSessionPayment.class);
        assertThat(responseBody.getData().paymentLink()).isNotBlank();
    }

    private void thenPaymentReceiverIsCreated() {
        Account account = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(account).isNotNull();

        PaymentReceiver paymentReceiver = paymentReceiverRepository.findByReceiverId(String.valueOf(account.getId()))
                .orElse(null);
        assertThat(paymentReceiver).isNotNull();
        assertThat(paymentReceiver.getReceiverType()).isEqualTo(PaymentReceiverType.DESIGN_FEE);
    }
}
