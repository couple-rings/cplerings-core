package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.CustomOrdersData;
import com.cplerings.core.api.order.request.ViewCustomOrdersRequest;
import com.cplerings.core.api.order.response.ViewCustomOrdersResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.CustomOrderTestHelper;

class ViewCustomOrdersUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    @Test
    void givenStaff_whenViewCustomOrders() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        customOrderTestHelper.createCustomOrder();
        ViewCustomOrdersRequest request = ViewCustomOrdersRequest.builder()
                .page(0)
                .pageSize(1)
                .branchId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CUSTOM_ORDERS_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomOrders(response);
    }

    private void thenResponseReturnCustomOrders(WebTestClient.ResponseSpec response) {
        final ViewCustomOrdersResponse responseBody = response.expectBody(ViewCustomOrdersResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final CustomOrdersData customOrdersData = responseBody.getData();
        assertThat(customOrdersData).isNotNull();
        assertThat(customOrdersData.getPage()).isZero();
        assertThat(customOrdersData.getPageSize()).isEqualTo(1);
        assertThat(customOrdersData.getItems()).hasSize(1);
    }
}
