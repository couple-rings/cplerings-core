package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.data.TransportationOrdersData;
import com.cplerings.core.api.transport.request.ViewTransportationOrdersRequest;
import com.cplerings.core.api.transport.response.ViewTransportationOrdersResponse;
import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

public class ViewTransportationUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenTransporterOrStaff_whenViewTransportationOrders() {
        var transportationOrder = transportOrderTestHelper.createTransportOrderWithWaitingStatusAndCustomOrderDone();
        transportationOrder.setTransporter(accountRepository.getReferenceById(51L));
        testDataSource.save(transportationOrder);
        String token = jwtTestHelper.generateToken(AccountTestConstant.TRANSPORTER_EMAIL);
        ViewTransportationOrdersRequest request = ViewTransportationOrdersRequest.builder()
                .page(0)
                .pageSize(1)
                .transporterId(51L)
                .status(ATransportationOrderStatus.WAITING)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.TRANSPORTATION_ORDER_PATH)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportationOrders(response);
    }

    private void thenResponseReturnTransportationOrders(WebTestClient.ResponseSpec response) {
        final ViewTransportationOrdersResponse responseBody = response.expectBody(ViewTransportationOrdersResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final TransportationOrdersData transportationOrdersData = responseBody.getData();
        assertThat(transportationOrdersData).isNotNull();
        assertThat(transportationOrdersData.getPage()).isZero();
        assertThat(transportationOrdersData.getPageSize()).isEqualTo(1);
        assertThat(transportationOrdersData.getItems()).hasSize(1);
        assertThat(transportationOrdersData.getTotalPages()).isEqualTo(1);
        assertThat(transportationOrdersData.getItems().stream().findFirst().get().getStatus()).isEqualByComparingTo(ATransportationOrderStatus.WAITING);
        assertThat(transportationOrdersData.getItems().stream().findFirst().get().getCustomOrder().getFirstRing()).isNotNull();
        assertThat(transportationOrdersData.getItems().stream().findFirst().get().getCustomOrder().getFirstRing().getDiamonds()).isNotNull();
        assertThat(transportationOrdersData.getItems().stream().findFirst().get().getCustomOrder().getFirstRing().getDiamonds()).isNotNull()
                .allSatisfy(diamond -> assertThat(diamond).isNotNull());
    }
}
