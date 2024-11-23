package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.request.ViewCustomOrdersRequest;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.response.GetTransportationOrderByCustomOrderResponse;
import com.cplerings.core.application.shared.entity.order.ATransportationOrder;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

public class GetTransportationOrderByCustomOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Test
    void givenStaff_whenViewCustomOrders() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        transportOrderTestHelper.createTransportOrder();
        ViewCustomOrdersRequest request = ViewCustomOrdersRequest.builder()
                .page(0)
                .pageSize(1)
                .branchId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_TRANSPORTATION_ORDER_BY_CUSTOM_ORDER_ID, 1)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportationOrders(response);
    }

    private void thenResponseReturnTransportationOrders(WebTestClient.ResponseSpec response) {
        final GetTransportationOrderByCustomOrderResponse responseBody = response.expectBody(GetTransportationOrderByCustomOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final ATransportationOrder transportationOrder = responseBody.getData();
        assertThat(transportationOrder).isNotNull();
        assertThat(transportationOrder.getCustomOrder()).isNotNull();
        assertThat(transportationOrder.getOrderNo()).isNotNull();
    }
}
