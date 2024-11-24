package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.request.data.UpdateImageDeliveryRequestData;
import com.cplerings.core.api.transport.response.UpdateImageDeliveryResponse;
import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

public class UpdateImageDeliveryUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Test
    void givenTransporter_whenUpdateTransportationOrder() {
        transportOrderTestHelper.createTransportOrderWithStatusDelivering();
        String token = jwtTestHelper.generateToken(AccountTestConstant.TRANSPORTER_EMAIL);
        UpdateImageDeliveryRequestData updateTransportationOrderStatusRequestData = UpdateImageDeliveryRequestData.builder()
                .imageId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.UPDATE_TRANSPORTATION_DELIVERY_IMAGE_ORDER_PATH, 1)
                .method(AbstractIT.RequestBuilder.Method.PUT)
                .authorizationHeader(token)
                .body(updateTransportationOrderStatusRequestData)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportationOrder(response);
    }

    private void thenResponseReturnTransportationOrder(WebTestClient.ResponseSpec response) {
        final UpdateImageDeliveryResponse responseBody = response.expectBody(UpdateImageDeliveryResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final TransportationOrder transportationOrder = responseBody.getData();
        assertThat(transportationOrder).isNotNull();
        assertThat(transportationOrder.transportationOrder().getStatus()).isEqualByComparingTo(ATransportationOrderStatus.DELIVERING);
        assertThat(transportationOrder.transportationOrder().getImage()).isNotNull();
        assertThat(transportationOrder.transportationOrder().getImage().getUrl()).isNotBlank();
    }
}
