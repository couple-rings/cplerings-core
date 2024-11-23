package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.data.TransportationOrderList;
import com.cplerings.core.api.transport.request.UpdateTransportationOrdersToOngoingRequest;
import com.cplerings.core.api.transport.response.UpdateTransportationOrdersToOngoingResponse;
import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

public class UpdateTransportationOrdersToOngoingUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Test
    void givenStaff_whenAssignTransportOrderUseCase() {
        transportOrderTestHelper.createTransportOrderWithWaitingStatus();
        String token = jwtTestHelper.generateToken(AccountTestConstant.TRANSPORTER_EMAIL);
        long[] idArray = {1};
        List<Long> ids = LongStream.of(idArray).boxed().collect(Collectors.toList());
        UpdateTransportationOrdersToOngoingRequest request = UpdateTransportationOrdersToOngoingRequest.builder()
                .transportationOrderIds(ids)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.UPDATE_TRANSPORTATION_ORDER_TO_ONGOING_PATH)
                .method(AbstractIT.RequestBuilder.Method.PUT)
                .authorizationHeader(token)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportationOrders(response);
    }

    private void thenResponseReturnTransportationOrders(WebTestClient.ResponseSpec response) {
        final UpdateTransportationOrdersToOngoingResponse responseBody = response.expectBody(UpdateTransportationOrdersToOngoingResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final TransportationOrderList transportationOrder = responseBody.getData();
        assertThat(transportationOrder).isNotNull();
        assertThat(transportationOrder.transportationOrders()).isNotNull();
        assertThat(transportationOrder.transportationOrders().get(0)).isNotNull();
        assertThat(transportationOrder.transportationOrders().get(0).getStatus()).isEqualByComparingTo(ATransportationOrderStatus.ON_GOING);
    }
}
