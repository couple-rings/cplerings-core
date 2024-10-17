package com.cplerings.core.test.integration.authentication;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.shared.ErrorCodesResponse;
import com.cplerings.core.api.shared.mapper.ErrorCodeResponseMapper;
import com.cplerings.core.application.authentication.error.AuthenticationErrorCode;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.hello.HelloController;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class AuthenticateUserUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenCustomer_whenPassingInAuthenticationJWTToAccessAPIForCustomerAndManager() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);

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

    @Test
    void givenManager_whenPassingInAuthenticationJWTToAccessAPIForCustomerAndManager() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(HelloController.TEST_HELLO_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();

        thenResponseIsOk(response);
        thenResponseBodyIsHelloMessage(response);
    }

    @Test
    void givenAnyoneNotCustomerOrManager_whenPassingInAuthenticationJWTToAccessAPIForCustomerAndManager() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.ADMIN_EMAIL);

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(HelloController.TEST_HELLO_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();

        thenResponseIsForbidden(response);
        thenResponseBodyIsError(response);
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
