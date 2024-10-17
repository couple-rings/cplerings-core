package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.account.data.Account;
import com.cplerings.core.api.account.response.AccountResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class ViewAccountUseCaseIT extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenAdmin_whenViewCustomerProfile() {
        final String adminToken = jwtTestHelper.generateToken(AccountTestConstant.ADMIN_EMAIL);
        final com.cplerings.core.domain.account.Account account = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(account).isNotNull();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.ACCOUNT_PATH.replace("{id}", Long.toString(account.getId())))
                .authorizationHeader(adminToken)
                .method(RequestBuilder.Method.GET)
                .send();

        thenAccountDetailOfCustomerIsReturned(response, account.getId());
    }

    private void thenAccountDetailOfCustomerIsReturned(WebTestClient.ResponseSpec response, Long id) {
        final AccountResponse responseBody = response.expectBody(AccountResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(Account.class);
        Account account = responseBody.getData();
        assertThat(account.email())
                .isNotNull()
                .isEqualTo(AccountTestConstant.CUSTOMER_EMAIL);
        assertThat(account.id())
                .isNotNull()
                .isEqualTo(id);
    }

    @Test
    void givenCustomer_whenViewOtherProfileIncludingThemselves() {
        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final com.cplerings.core.domain.account.Account account = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(account).isNotNull();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.ACCOUNT_PATH.replace("{id}", Long.toString(account.getId())))
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsForbidden(response);
    }
}
