package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.account.data.Profile;
import com.cplerings.core.api.account.response.ProfileResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class ViewCurrentProfileUseCaseIT extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

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
                .isExactlyInstanceOf(Profile.class);

        final Profile profile = responseBody.getData();
        assertThat(profile.id()).isEqualTo(account.getId());
        assertThat(profile.email()).isEqualTo(account.getEmail());
        assertThat(profile.username()).isEqualTo(account.getUsername());
        assertThat(profile.phone()).isEqualTo(account.getPhone());
        assertThat(profile.avatar()).isEqualTo(account.getAvatar());
    }
}
