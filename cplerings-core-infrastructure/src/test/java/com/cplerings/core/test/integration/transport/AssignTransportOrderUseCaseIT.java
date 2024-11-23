package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.request.data.AssignTransportOrderRequestData;
import com.cplerings.core.api.transport.response.AssignTransportOrderResponse;
import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

class AssignTransportOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Test
    void givenStaff_whenAssignTransportOrderUseCase() {
        transportOrderTestHelper.createTransportOrder();
        String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        AssignTransportOrderRequestData request = AssignTransportOrderRequestData.builder()
                .transporterId(51L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.ASSIGN_TRANSPORTATION_ORDER_PATH.replace("{transportationOrderId}", Long.toString(1L)))
                .method(AbstractIT.RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportationOrder(response);
    }

    private void thenResponseReturnTransportationOrder(WebTestClient.ResponseSpec response) {
        final AssignTransportOrderResponse responseBody = response.expectBody(AssignTransportOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final TransportationOrder transportationOrder = responseBody.getData();
        assertThat(transportationOrder).isNotNull();
        assertThat(transportationOrder.transportationOrder().getTransporter()).isNotNull();
        assertThat(transportationOrder.transportationOrder().getStatus()).isEqualByComparingTo(ATransportationOrderStatus.WAITING);
    }
}
