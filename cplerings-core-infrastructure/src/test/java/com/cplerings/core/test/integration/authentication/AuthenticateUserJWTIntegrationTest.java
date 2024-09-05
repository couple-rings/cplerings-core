package com.cplerings.core.test.integration.authentication;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.test.integration.AbstractIntegrationTest;
import com.cplerings.core.test.integration.api.TestController;
import com.cplerings.core.test.integration.helper.AccountTestHelper;
import com.cplerings.core.test.integration.helper.JWTTestHelper;

class AuthenticateUserJWTIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AccountTestHelper accountTestHelper;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenCustomer_whenPassingInAuthenticationJWT() {
        final Account account = accountTestHelper.createOne();
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
}
