package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.request.CreateTransportationNoteRequest;
import com.cplerings.core.api.transport.response.CreateTransportationNoteResponse;
import com.cplerings.core.application.shared.entity.transport.ATransportationNote;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

public class CreateTransportationNoteUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Test
    void givenCustomer_whenCreateAddress() {
        transportOrderTestHelper.createTransportOrderWithStatusDelivering();
        String token = jwtTestHelper.generateToken(AccountTestConstant.TRANSPORTER_EMAIL);
        CreateTransportationNoteRequest request = CreateTransportationNoteRequest.builder()
                .transportationOrderId(1L)
                .note("test")
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CREATE_TRANSPORTATION_NOTE_PATH)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportNote(response);
    }

    private void thenResponseReturnTransportNote(WebTestClient.ResponseSpec response) {
        final CreateTransportationNoteResponse responseBody = response.expectBody(CreateTransportationNoteResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final ATransportationNote transportationNote = responseBody.getData();
        assertThat(transportationNote).isNotNull();
        assertThat(transportationNote.getNote()).isNotNull();
        assertThat(transportationNote.getDate()).isNotNull();
    }
}
