package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.StandardOrdersData;
import com.cplerings.core.api.order.request.ViewStandardOrdersRequest;
import com.cplerings.core.api.order.response.ViewStandardOrdersResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.StandardOrderTestHelper;

public class ViewStandardOrdersUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private StandardOrderTestHelper standardOrderTestHelper;

    @Test
    void givenStaff_whenViewCustomOrders() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        standardOrderTestHelper.createStandardOrder();
        ViewStandardOrdersRequest request = ViewStandardOrdersRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.STANDARD_ORDER_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnStandardOrders(response);
    }

    private void thenResponseReturnStandardOrders(WebTestClient.ResponseSpec response) {
        final ViewStandardOrdersResponse responseBody = response.expectBody(ViewStandardOrdersResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final StandardOrdersData standardOrdersData = responseBody.getData();
        assertThat(standardOrdersData).isNotNull();
        assertThat(standardOrdersData.getPage()).isZero();
        assertThat(standardOrdersData.getPageSize()).isEqualTo(1);
        assertThat(standardOrdersData.getItems()).hasSize(1);
    }
}
