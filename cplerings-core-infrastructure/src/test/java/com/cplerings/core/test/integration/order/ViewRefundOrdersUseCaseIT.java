package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.RefundsData;
import com.cplerings.core.api.order.request.ViewRefundOrdersRequest;
import com.cplerings.core.api.order.response.ViewRefundOrdersResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.RefundTestHelper;

public class ViewRefundOrdersUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private RefundTestHelper refundTestHelper;

    @Test
    void givenManager_whenViewRefundOrders() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);
        refundTestHelper.createRefund();
        ViewRefundOrdersRequest request = ViewRefundOrdersRequest.builder()
                .page(0)
                .pageSize(1)
                .staffId(21L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.REFUNDS_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnStandardOrders(response);
    }

    private void thenResponseReturnStandardOrders(WebTestClient.ResponseSpec response) {
        final ViewRefundOrdersResponse responseBody = response.expectBody(ViewRefundOrdersResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final RefundsData refundsData = responseBody.getData();
        assertThat(refundsData).isNotNull();
        assertThat(refundsData.getPage()).isZero();
        assertThat(refundsData.getPageSize()).isEqualTo(1);
        assertThat(refundsData.getItems()).hasSize(1);
        assertThat(refundsData.getItems()).isNotNull().allSatisfy(x -> {
            assertThat(x.getStaff()).isNotNull();
        });
    }
}
