package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.data.TransportationNotesData;
import com.cplerings.core.api.transport.request.ViewTransportationNotesRequest;
import com.cplerings.core.api.transport.response.ViewTransportationNotesResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.order.status.TransportationNote;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

public class ViewTransportationNotesUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenTransporterOrStaff_whenViewTransportationNotes() {
        var transportationOrder = transportOrderTestHelper.createTransportOrderWithStatusDelivering();
        transportationOrder.setTransporter(accountRepository.getReferenceById(51L));
        testDataSource.save(transportationOrder);
        String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        TransportationNote transportationNote = TransportationNote.builder()
                .date(TemporalUtils.getCurrentInstantUTC())
                .note("Test")
                .transportationOrder(transportationOrder)
                .build();
        testDataSource.save(transportationNote);
        ViewTransportationNotesRequest request = ViewTransportationNotesRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.TRANSPORTATION_NOTE_PATH)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportationNotes(response);
    }

    private void thenResponseReturnTransportationNotes(WebTestClient.ResponseSpec response) {
        final ViewTransportationNotesResponse responseBody = response.expectBody(ViewTransportationNotesResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final TransportationNotesData transportationNotesData = responseBody.getData();
        assertThat(transportationNotesData).isNotNull();
        assertThat(transportationNotesData.getPage()).isZero();
        assertThat(transportationNotesData.getPageSize()).isEqualTo(1);
        assertThat(transportationNotesData.getItems()).hasSize(1);
        assertThat(transportationNotesData.getTotalPages()).isEqualTo(1);
    }
}
