package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.account.response.GetRandomStaffResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class GetRandomStaffUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenService_whenRandomStaff() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.SERVICE_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.GET_RANDOM_STAFF_PATH)
                .authorizationHeader(token)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenResponseHasRandomizedStaff(response);
    }

    private void thenResponseHasRandomizedStaff(WebTestClient.ResponseSpec response) {
        final GetRandomStaffResponse responseBody = response.expectBody(GetRandomStaffResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final AAccount staff = responseBody.getData();
        assertThat(staff).isNotNull();
    }
}
