package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.account.request.VerifyCustomerRequest;
import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.service.verification.error.AccountVerificationServiceErrorCode;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.domain.account.VerificationCodeStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

public class VerifyCustomerUseCaseIT extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenAnyone_whenVerifyTheirAccount() {
        Account customerAccount = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(customerAccount).isNotNull();
        customerAccount.setStatus(AccountStatus.VERIFYING);
        accountRepository.save(customerAccount);

        final AccountVerification accountVerification = AccountVerification.builder()
                .account(customerAccount)
                .code("123456")
                .status(VerificationCodeStatus.PENDING)
                .build();
        testDataSource.save(accountVerification);

        final VerifyCustomerRequest request = VerifyCustomerRequest.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .verificationCode("123456")
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VERIFY_CUSTOMER_PATH)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenResponseContainsAuthenticationToken(response);
    }

    @Test
    void givenAnyone_whenVerifyTheirAccountWithDisabledVerificationCode() {
        Account customerAccount = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(customerAccount).isNotNull();
        customerAccount.setStatus(AccountStatus.VERIFYING);
        accountRepository.save(customerAccount);

        AccountVerification accountVerification = AccountVerification.builder()
                .account(customerAccount)
                .code("123456")
                .status(VerificationCodeStatus.PENDING)
                .state(State.INACTIVE)
                .build();
        testDataSource.save(accountVerification);

        final VerifyCustomerRequest request = VerifyCustomerRequest.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .verificationCode("123456")
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VERIFY_CUSTOMER_PATH)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsBadRequestWithErrorCode(response, AccountVerificationServiceErrorCode.ACCOUNT_VERIFICATION_CODE_IS_INACTIVE);
    }

    private void thenResponseContainsAuthenticationToken(WebTestClient.ResponseSpec response) {
        final AuthenticationTokenResponse responseBody = response.expectBody(AuthenticationTokenResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(AuthenticationToken.class);

        final AuthenticationToken authenticationToken = responseBody.getData();
        assertThat(authenticationToken.token())
                .isNotBlank();
        assertThat(authenticationToken.refreshToken())
                .isNotBlank();
    }
}
