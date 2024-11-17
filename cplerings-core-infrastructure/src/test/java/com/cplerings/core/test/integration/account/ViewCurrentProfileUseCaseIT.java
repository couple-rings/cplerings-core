package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.account.data.ProfileData;
import com.cplerings.core.api.account.response.ProfileResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class ViewCurrentProfileUseCaseIT extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private SpouseTestHelper spouseTestHelper;

    @Test
    void givenAnyone_whenViewingTheirOwnProfile() {
        final Account account = accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL)
                .orElse(null);
        assertThat(account).isNotNull();

        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CURRENT_PROFILE_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenResponseHasProfileMatchesTheAccount(response, account);
    }

    private void thenResponseHasProfileMatchesTheAccount(WebTestClient.ResponseSpec response, Account account) {
        final ProfileResponse responseBody = response.expectBody(ProfileResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(ProfileData.class);

        final ProfileData profile = responseBody.getData();
        assertThat(profile.account().getId()).isEqualTo(account.getId());
        assertThat(profile.account().getEmail()).isEqualTo(account.getEmail());
        assertThat(profile.account().getUsername()).isEqualTo(account.getUsername());
        assertThat(profile.account().getPhone()).isEqualTo(account.getPhone());
        assertThat(profile.account().getAvatar()).isEqualTo(account.getAvatar());
    }

    @Test
    void givenCustomer_whenViewingTheirOwnProfileWithSpouseRegistered() {
        spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);

        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CURRENT_PROFILE_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenResponseHasSpouseStatusTrue(response);
    }

    private void thenResponseHasSpouseStatusTrue(WebTestClient.ResponseSpec response) {
        final ProfileResponse responseBody = response.expectBody(ProfileResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(ProfileData.class);

        final ProfileData profile = responseBody.getData();
        assertThat(profile.hasSpouse()).isTrue();
    }

    @Test
    void givenCustomer_whenViewingTheirOwnProfileWithSpouseNotRegistered() {
        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CURRENT_PROFILE_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenResponseHasSpouseStatusFalse(response);
    }

    private void thenResponseHasSpouseStatusFalse(WebTestClient.ResponseSpec response) {
        final ProfileResponse responseBody = response.expectBody(ProfileResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(ProfileData.class);

        final ProfileData profile = responseBody.getData();
        assertThat(profile.hasSpouse()).isFalse();
    }

    @Test
    void givenNotCustomer_whenViewingTheirOwnProfileWithSpouseNotRegistered() {
        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.JEWELER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CURRENT_PROFILE_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenResponseHasSpouseStatusFalse(response);
    }
}
