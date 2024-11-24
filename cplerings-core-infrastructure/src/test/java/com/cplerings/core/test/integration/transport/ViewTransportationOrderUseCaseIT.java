package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.response.ViewTransportationOrderResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

public class ViewTransportationOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void givenStaff_whenViewTransportationOrder() {
        String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);

        var transportationOrder = transportOrderTestHelper.createTransportOrderWithWaitingStatusAndCustomOrderDone();
        transportationOrder.setTransporter(accountRepository.getReferenceById(51L));
        testDataSource.save(transportationOrder);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_TRANSPORTATION_ORDER_DETAIL, 1)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();

        thenResponseIsOk(response);
        thenReturnTransportationOrder(response);
    }

    private void thenReturnTransportationOrder(WebTestClient.ResponseSpec response) {
        final ViewTransportationOrderResponse responseBody = response.expectBody(ViewTransportationOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(TransportationOrder.class);
        assertThat(responseBody.getData().transportationOrder()).isNotNull();
        assertThat(responseBody.getData().transportationOrder().getOrderNo()).isNotNull();
        assertThat(responseBody.getData().transportationOrder().getTransporter()).isNotNull();
        assertThat(responseBody.getData().transportationOrder().getCustomOrder()).isNotNull();
        assertThat(responseBody.getData().transportationOrder().getCustomOrder().getFirstRing()).isNotNull();
        assertThat(responseBody.getData().transportationOrder().getCustomOrder().getFirstRing().getDiamonds()).isNotNull();
        assertThat(responseBody.getData().transportationOrder().getCustomOrder().getFirstRing().getDiamonds()).isNotNull()
                .allSatisfy(diamond -> assertThat(diamond).isNotNull());
    }
}
