package com.cplerings.core.test.integration.authentication;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.AbstractResponse;
import com.cplerings.core.api.ErrorCodesResponse;
import com.cplerings.core.api.mapper.ErrorCodeResponseMapper;
import com.cplerings.core.application.authentication.error.AuthenticationErrorCode;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.test.integration.AbstractIT;
import com.cplerings.core.test.integration.api.TestController;
import com.cplerings.core.test.integration.helper.AccountTestHelper;
import com.cplerings.core.test.integration.helper.JWTTestHelper;

class AuthenticateUserJWTIT extends AbstractIT {

    @Autowired
    private AccountTestHelper accountTestHelper;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenCustomer_whenPassingInAuthenticationJWTToAccessAPIForCustomer() {
        final Account account = accountTestHelper.createCustomer();
        final String token = jwtTestHelper.generateToken(account.getEmail());

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
    void givenAnyoneNotCustomer_whenPassingInAuthenticationJWTToAccessAPIForCustomer() {
        final Account account = accountTestHelper.createOneWithRole(Role.ADMIN);
        final String token = jwtTestHelper.generateToken(account.getEmail());

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
