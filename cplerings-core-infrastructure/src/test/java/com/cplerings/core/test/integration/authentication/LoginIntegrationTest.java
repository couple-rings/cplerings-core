package com.cplerings.core.test.integration.authentication;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.application.authentication.output.AuthenticationTokenOutput;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationService;
import com.cplerings.core.test.integration.AbstractIntegrationTest;
import com.cplerings.core.test.integration.helper.AccountTestHelper;

class LoginIntegrationTest extends AbstractIntegrationTest {

    private static final String LOGIN_PATH = "/auth/login";

    @Autowired
    private AccountTestHelper accountTestHelper;

    @Autowired
    private JWTVerificationService jwtVerificationService;

    @Test
    void givenAnyone_whenLogin() {
        accountTestHelper.createCustomer();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(LOGIN_PATH)
                .method(RequestBuilder.Method.POST)
                .body(new LoginCredentialRequest(AccountTestHelper.DEFAULT_EMAIL, AccountTestHelper.DEFAULT_PASSWORD))
                .send();

        thenResponseIsOk(response);
        final AuthenticationTokenOutput authenticationTokenOutput = thenTokenAndRefreshTokenAreReturned(response);
        thenBothTokensAreValid(authenticationTokenOutput);
    }

    private void thenResponseIsOk(WebTestClient.ResponseSpec response) {
        response.expectStatus().isOk();
    }

    private AuthenticationTokenOutput thenTokenAndRefreshTokenAreReturned(WebTestClient.ResponseSpec response) {
        final AuthenticationTokenOutput output = response.expectBody(AuthenticationTokenOutput.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(output)
                .isNotNull();
        Assertions.assertThat(output.getToken())
                .isNotBlank();
        Assertions.assertThat(output.getRefreshToken())
                .isNotBlank();
        return output;
    }

    private void thenBothTokensAreValid(AuthenticationTokenOutput authenticationTokenOutput) {
        final String token = authenticationTokenOutput.getToken();
        final JWTVerificationResult tokenResult = jwtVerificationService.validateToken(token);
        Assertions.assertThat(tokenResult.getStatus())
                .isEqualTo(JWTVerificationResult.Status.VALID);
        Assertions.assertThat(tokenResult.getSubject())
                .isEqualTo(AccountTestHelper.DEFAULT_EMAIL);

        final String refreshToken = authenticationTokenOutput.getRefreshToken();
        final JWTVerificationResult refreshResult = jwtVerificationService.validateRefreshToken(refreshToken);
        Assertions.assertThat(refreshResult.getStatus())
                .isEqualTo(JWTVerificationResult.Status.VALID);
        Assertions.assertThat(refreshResult.getSubject())
                .isEqualTo(AccountTestHelper.DEFAULT_EMAIL);
    }
}
