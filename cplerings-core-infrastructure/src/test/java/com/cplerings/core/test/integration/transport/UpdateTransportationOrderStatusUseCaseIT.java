package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.request.data.UpdateTransportationOrderStatusRequestData;
import com.cplerings.core.api.transport.response.UpdateTransportationOrderStatusResponse;
import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

class UpdateTransportationOrderStatusUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Test
    void givenTransporter_whenUpdateTransportationOrder() {
        transportOrderTestHelper.createTransportOrderWithOnGoingStatus();
        String token = jwtTestHelper.generateToken(AccountTestConstant.TRANSPORTER_EMAIL);
        UpdateTransportationOrderStatusRequestData updateTransportationOrderStatusRequestData = UpdateTransportationOrderStatusRequestData.builder()
                .status(ATransportationOrderStatus.DELIVERING)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.UPDATE_TRANSPORTATION_ORDER_STATUS.replace("{transportationOrderId}", Long.toString(1L)))
                .method(AbstractIT.RequestBuilder.Method.PUT)
                .authorizationHeader(token)
                .body(updateTransportationOrderStatusRequestData)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportationOrder(response);
    }

    private void thenResponseReturnTransportationOrder(WebTestClient.ResponseSpec response) {
        final UpdateTransportationOrderStatusResponse responseBody = response.expectBody(UpdateTransportationOrderStatusResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final TransportationOrder transportationOrder = responseBody.getData();
        assertThat(transportationOrder).isNotNull();
        assertThat(transportationOrder.transportationOrder().getStatus()).isEqualByComparingTo(ATransportationOrderStatus.DELIVERING);
    }
}
