package com.cplerings.core.test.integration.customrequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.data.CustomRequestsData;
import com.cplerings.core.api.design.request.ViewCustomRequestsRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.repository.CustomRequestRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class ViewCustomRequestsUseCaseIT extends AbstractIT {

    @Autowired
    private CustomRequestRepository customRequestRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenAnyone_whenViewCustomRequests() {
        String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        CustomRequest customRequest = CustomRequest.builder()
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
        ViewCustomRequestsRequest request = ViewCustomRequestsRequest.builder()
                .status(ACustomRequestStatus.PENDING)
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_CUSTOM_REQUESTS_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomRequests(response);

    }

    private void thenResponseReturnCustomRequests(WebTestClient.ResponseSpec response) {
        final ViewCustomRequestsResponse responseBody = response.expectBody(ViewCustomRequestsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final CustomRequestsData designCoupleData = responseBody.getData();
        assertThat(designCoupleData).isNotNull();
        assertThat(designCoupleData.getPage()).isZero();
        assertThat(designCoupleData.getPageSize()).isEqualTo(1);
        assertThat(designCoupleData.getItems()).hasSize(1);
        assertThat(designCoupleData.getTotalPages()).isEqualTo(1);
    }
}
