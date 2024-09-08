package com.cplerings.core.test.integration.authentication;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.AbstractResponse;
import com.cplerings.core.api.ErrorCodesResponse;
import com.cplerings.core.api.mapper.ErrorCodeResponseMapper;
import com.cplerings.core.application.authentication.error.AuthenticationErrorCode;
import com.cplerings.core.test.integration.AbstractIT;
import com.cplerings.core.test.integration.internal.TestController;
import com.cplerings.core.test.integration.internal.helper.AccountTestConstant;
import com.cplerings.core.test.integration.internal.helper.JWTTestHelper;

class AuthenticateUserJWTIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenCustomer_whenPassingInAuthenticationJWTToAccessAPIForCustomerAndManager() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(TestController.TEST_HELLO_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();

        thenResponseIsOk(response);
        thenResponseBodyIsHelloMessage(response);
    }

    @Test
    void givenManager_whenPassingInAuthenticationJWTToAccessAPIForCustomerAndManager() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(TestController.TEST_HELLO_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();

        thenResponseIsOk(response);
        thenResponseBodyIsHelloMessage(response);
    }

    private void thenResponseIsOk(WebTestClient.ResponseSpec response) {
        response.expectStatus().isOk();
    }

    private void thenResponseBodyIsHelloMessage(WebTestClient.ResponseSpec response) {
        final String helloMessage = response.expectBody(String.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(helloMessage)
                .isEqualTo(TestController.DEFAULT_HELLO_MESSAGE);
    }

    @Test
    void givenAnyoneNotCustomerOrManager_whenPassingInAuthenticationJWTToAccessAPIForCustomerAndManager() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.ADMIN_EMAIL);

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(TestController.TEST_HELLO_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();

        thenResponseIsForbidden(response);
        thenResponseBodyIsError(response);
    }

    private void thenResponseIsForbidden(WebTestClient.ResponseSpec response) {
        response.expectStatus().isForbidden();
    }

    private void thenResponseBodyIsError(WebTestClient.ResponseSpec response) {
        final ErrorCodesResponse error = response.expectBody(ErrorCodesResponse.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertThat(error)
                .isNotNull();
        Assertions.assertThat(error.getType())
                .isEqualTo(AbstractResponse.Type.ERROR);
        Assertions.assertThat(error.getErrors())
                .hasSize(1)
                .containsExactly(ErrorCodeResponseMapper.INSTANCE.toResponse(AuthenticationErrorCode.UNAUTHORIZED));
    }
}
