package com.cplerings.core.test.integration.customrequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.request.data.DetermineCustomRequestRequestData;
import com.cplerings.core.api.design.response.DetermineCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class DetermineCustomRequestUseCaseIT extends AbstractIT {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenStaff_whenDetermineCustomRequest() {

        String token = jwtTestHelper.generateToken("staff@cplerings.com");
        CustomRequest customRequest = CustomRequest.builder()
                .id(1L)
                .comment("abc")
                .status(CustomRequestStatus.PENDING)
                .state(State.ACTIVE)
                .customer(Account.builder().id(1L).build())
                .build();
        testDataSource.save(customRequest);
        final DetermineCustomRequestRequestData request = DetermineCustomRequestRequestData.builder()
                .staffId(21L)
                .customRequestStatus(ACustomRequestStatus.APPROVED)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DETERMINE_CUSTOM_REQUEST_PATH.replace("{customRequestId}", Long.toString(1L)))
                .authorizationHeader(token)
                .method(RequestBuilder.Method.PUT)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomRequestUpdatedData(response);
    }

    private void thenResponseReturnCustomRequestUpdatedData(WebTestClient.ResponseSpec response) {
        final DetermineCustomRequestResponse responseBody = response.expectBody(DetermineCustomRequestResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(com.cplerings.core.api.design.data.CustomRequest.class);
        assertThat(responseBody.getData().customRequest()).isNotNull();
    }
}
