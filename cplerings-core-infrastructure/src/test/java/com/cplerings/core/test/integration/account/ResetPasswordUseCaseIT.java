package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.account.request.ResetPasswordRequest;
import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountPasswordReset;
import com.cplerings.core.domain.account.ResetCodeStatus;
import com.cplerings.core.infrastructure.repository.AccountPasswordResetRepository;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.AccountHelper;

class ResetPasswordUseCaseIT extends AbstractIT {

    private static final String TEST_NEW_PASSWORD = "newP@ssw0rd";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHelper accountHelper;

    @Autowired
    private AccountPasswordResetRepository accountPasswordResetRepository;

    @Autowired
    private PasswordService passwordService;

    @Test
    void givenAnyone_whenVerifyTheirAccount() {
        Account customerAccount = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(customerAccount).isNotNull();
        final String oldPasswordHash = customerAccount.getPassword();
        final AccountPasswordReset accountPasswordReset = AccountPasswordReset.builder()
                .account(customerAccount)
                .code("123456")
                .status(ResetCodeStatus.PENDING)
                .build();
        accountHelper.updateAuditor(accountPasswordReset);
        accountPasswordResetRepository.save(accountPasswordReset);

        final ResetPasswordRequest request = ResetPasswordRequest.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .newPassword(TEST_NEW_PASSWORD)
                .otp("123456")
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.RESET_PASSWORD_PATH)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenNoResponseIsReturned(response);
        thenAccountWillHaveNewPassword(oldPasswordHash);
    }

    private void thenAccountWillHaveNewPassword(String oldPasswordHash) {
        Account customerAccount = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(customerAccount).isNotNull();
        assertThat(passwordService.passwordMatchesEncrypted(TEST_NEW_PASSWORD, oldPasswordHash)).isFalse();
        assertThat(passwordService.passwordMatchesEncrypted(TEST_NEW_PASSWORD, customerAccount.getPassword())).isTrue();
    }
}
