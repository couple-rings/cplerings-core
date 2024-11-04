package com.cplerings.core.test.integration.customrequest;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.design.data.CustomRequestData;
import com.cplerings.core.api.design.request.ViewCustomRequestRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.repository.CustomRequestRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;

class ViewSingleCustomRequestUseCaseIT extends AbstractIT {

    @Autowired
    private CustomRequestRepository customRequestRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenAnyone_whenViewCoupleDesign() {
        ViewCustomRequestRequest request = new ViewCustomRequestRequest(1L);

        String token = jwtTestHelper.generateToken("staff@cplerings.com");

        com.cplerings.core.domain.design.request.CustomRequest customRequest =
                com.cplerings.core.domain.design.request.CustomRequest.builder()
                        .id(1L)
                        .comment("abc")
                        .status(CustomRequestStatus.PENDING)
                        .state(State.ACTIVE)
                        .createdBy("abc")
                        .createdAt(Instant.now())
                        .modifiedAt(Instant.now())
                        .modifiedBy("abc")
                        .customer(Account.builder().id(1L).build())
                        .build();
        customRequestRepository.save(customRequest);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CUSTOM_SINGLE_REQUEST_PATH.replace("{customRequestId}", Long.toString(customRequest.getId())))
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomRequestDetail(response);
    }

    private void thenResponseReturnCustomRequestDetail(WebTestClient.ResponseSpec response) {
        final ViewCustomRequestResponse responseBody = response.expectBody(ViewCustomRequestResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final CustomRequestData customRequestData = responseBody.getData();
        assertThat(customRequestData).isNotNull();
    }
}
