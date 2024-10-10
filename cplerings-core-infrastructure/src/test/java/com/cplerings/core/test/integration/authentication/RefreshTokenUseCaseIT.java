package com.cplerings.core.test.integration.authentication;

import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.authentication.request.RefreshTokenRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.hello.HelloController;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class RefreshTokenUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenAnyone_whenRefreshToken() {
        final String refreshToken = jwtTestHelper.generateRefreshToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.REFRESH_TOKEN_PATH)
                .method(RequestBuilder.Method.POST)
                .body(new RefreshTokenRequest(refreshToken))
                .send();

        thenResponseIsOk(response);
        final AuthenticationToken authenticationToken = thenBodyHasTwoToken(response);
        thenReturnedTokenCanBeUsedToLogin(authenticationToken.token());
    }

    private AuthenticationToken thenBodyHasTwoToken(WebTestClient.ResponseSpec response) {
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

    private void thenReturnedTokenCanBeUsedToLogin(String token) {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(HelloController.TEST_HELLO_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();

        thenResponseIsOk(response);
        thenResponseBodyIsHelloMessage(response);
    }

    private void thenResponseBodyIsHelloMessage(WebTestClient.ResponseSpec response) {
        final String helloMessage = response.expectBody(String.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(helloMessage)
                .isEqualTo(HelloController.DEFAULT_HELLO_MESSAGE);
    }
}
