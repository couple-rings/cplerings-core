package com.cplerings.core.test.integration.dashboard;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.dashboard.data.ViewBranchRevenueData;
import com.cplerings.core.api.dashboard.request.ViewBranchRevenueRequest;
import com.cplerings.core.api.dashboard.response.ViewBranchRevenueResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.CustomOrderTestHelper;

class ViewRevenueUseCaseIT extends AbstractIT {

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenAnyone_whenViewCoupleDesign() {
        customOrderTestHelper.createCompleteCustomOrder();
        String token = jwtTestHelper.generateToken("manager@cplerings.com");
        ViewBranchRevenueRequest request = ViewBranchRevenueRequest.builder()
                .startDate(Instant.now().minus(1L, ChronoUnit.DAYS))
                .endDate(Instant.now().plus(2L, ChronoUnit.DAYS))
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.REVENUE_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnRevenue(response);
    }

    private void thenResponseReturnRevenue(WebTestClient.ResponseSpec response) {
        final ViewBranchRevenueResponse responseBody = response.expectBody(ViewBranchRevenueResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final ViewBranchRevenueData customRequestData = responseBody.getData();
        assertThat(customRequestData).isNotNull();
        assertThat(customRequestData.totalRevenue()).isNotNull();
    }
}
