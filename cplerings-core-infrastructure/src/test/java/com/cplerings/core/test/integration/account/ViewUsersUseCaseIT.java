package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.account.data.UsersData;
import com.cplerings.core.api.account.request.ViewUsersRequest;
import com.cplerings.core.api.account.response.ViewUsersResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

class ViewUsersUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenService_whenGetListOfUsers() {
        final Set<Long> userIds = Set.of(1L, 21L, 41L);
        final String token = jwtTestHelper.generateToken(AccountTestConstant.SERVICE_EMAIL);
        final ViewUsersRequest request = ViewUsersRequest.builder()
                .userIds(userIds)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.USERS_PATH)
                .authorizationHeader(token)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenResponseHasUsersData(response);
    }

    private void thenResponseHasUsersData(WebTestClient.ResponseSpec response) {
        final ViewUsersResponse responseBody = response.expectBody(ViewUsersResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final UsersData usersData = responseBody.getData();
        assertThat(usersData).isNotNull();
        assertThat(usersData.users()).hasSize(3);
    }
}
