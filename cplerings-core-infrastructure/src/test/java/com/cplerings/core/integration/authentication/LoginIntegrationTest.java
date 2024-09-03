package com.cplerings.core.integration.authentication;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.helper.AccountTestHelper;
import com.cplerings.core.integration.AbstractIntegrationTest;

class LoginIntegrationTest extends AbstractIntegrationTest {

    private static final String LOGIN_PATH = "/auth/login";

    @Autowired
    private AccountTestHelper accountTestHelper;

    @Test
    @WithAnonymousUser
    void givenAnyone_whenLogin() {
        accountTestHelper.createOne();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(LOGIN_PATH)
                .method(RequestBuilder.Method.POST)
                .body(new LoginCredentialRequest(AccountTestHelper.DEFAULT_EMAIL, AccountTestHelper.DEFAULT_PASSWORD))
                .send();

        thenResponseIsOk(response);
        thenTokenAndRefreshTokenAreReturned(response);
    }

    private void thenResponseIsOk(WebTestClient.ResponseSpec response) {
        response.expectStatus().isOk();
    }

    private void thenTokenAndRefreshTokenAreReturned(WebTestClient.ResponseSpec response) {
        final AuthenticationTokenOutput output = response.expectBody(AuthenticationTokenOutput.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(output)
                .isNotNull();
        Assertions.assertThat(output.getToken())
                .isNotBlank();
        Assertions.assertThat(output.getRefreshToken())
                .isNotBlank();
    }
}
