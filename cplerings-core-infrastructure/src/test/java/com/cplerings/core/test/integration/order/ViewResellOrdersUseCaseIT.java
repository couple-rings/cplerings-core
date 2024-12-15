package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.RefundsData;
import com.cplerings.core.api.order.data.ResellOrdersData;
import com.cplerings.core.api.order.request.ViewRefundOrdersRequest;
import com.cplerings.core.api.order.request.ViewResellOrdersRequest;
import com.cplerings.core.api.order.response.ViewRefundOrdersResponse;
import com.cplerings.core.api.order.response.ViewResellOrdersResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.ResellTestHelper;

public class ViewResellOrdersUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private ResellTestHelper resellTestHelper;

    @Test
    void givenManager_whenViewResellOrders() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);
        resellTestHelper.createResellOrder();
        ViewResellOrdersRequest request = ViewResellOrdersRequest.builder()
                .page(0)
                .pageSize(1)
                .staffId(21L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.RESELL_ORDERS_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnResellOrders(response);
    }

    private void thenResponseReturnResellOrders(WebTestClient.ResponseSpec response) {
        final ViewResellOrdersResponse responseBody = response.expectBody(ViewResellOrdersResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final ResellOrdersData resellOrdersData = responseBody.getData();
        assertThat(resellOrdersData).isNotNull();
        assertThat(resellOrdersData.getPage()).isZero();
        assertThat(resellOrdersData.getPageSize()).isEqualTo(1);
        assertThat(resellOrdersData.getItems()).hasSize(1);
        assertThat(resellOrdersData.getItems()).isNotNull().allSatisfy(x -> {
            assertThat(x.getStaff()).isNotNull();
        });
    }
}
