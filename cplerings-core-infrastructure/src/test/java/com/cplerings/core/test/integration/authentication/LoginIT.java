package com.cplerings.core.test.integration.authentication;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationService;
import com.cplerings.core.test.integration.shared.AbstractIT;
import com.cplerings.core.test.integration.shared.helper.AccountTestConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class LoginIT extends AbstractIT {

    private static final String LOGIN_PATH = "/auth/login";

    @Autowired
    private JWTVerificationService jwtVerificationService;

    @Test
    void givenAnyone_whenLogin() {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(LOGIN_PATH)
                .method(RequestBuilder.Method.POST)
                .body(new LoginCredentialRequest(AccountTestConstant.CUSTOMER_EMAIL, AccountTestConstant.PASSWORD))
                .send();

        thenResponseIsOk(response);
        final AuthenticationToken token = thenTokenAndRefreshTokenAreReturned(response);
        thenBothTokensAreValid(token);
    }

    private void thenResponseIsOk(WebTestClient.ResponseSpec response) {
        response.expectStatus().isOk();
    }

    private AuthenticationToken thenTokenAndRefreshTokenAreReturned(WebTestClient.ResponseSpec response) {
        final AuthenticationTokenResponse responseBody = response.expectBody(AuthenticationTokenResponse.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(responseBody)
                .isNotNull();
        Assertions.assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        Assertions.assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(AuthenticationToken.class);
        return responseBody.getData();
    }

    private void thenBothTokensAreValid(AuthenticationToken authenticationToken) {
        final String token = authenticationToken.token();
        final JWTVerificationResult tokenResult = jwtVerificationService.validateToken(token);
        Assertions.assertThat(tokenResult.getStatus())
                .isEqualTo(JWTVerificationResult.Status.VALID);
        Assertions.assertThat(tokenResult.getSubject())
                .isEqualTo(AccountTestConstant.CUSTOMER_EMAIL);

        final String refreshToken = authenticationToken.refreshToken();
        final JWTVerificationResult refreshResult = jwtVerificationService.validateRefreshToken(refreshToken);
        Assertions.assertThat(refreshResult.getStatus())
                .isEqualTo(JWTVerificationResult.Status.VALID);
        Assertions.assertThat(refreshResult.getSubject())
                .isEqualTo(AccountTestConstant.CUSTOMER_EMAIL);
    }
}
