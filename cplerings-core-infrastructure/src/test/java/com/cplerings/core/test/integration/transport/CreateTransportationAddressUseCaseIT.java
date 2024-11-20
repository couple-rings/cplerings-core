package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.request.CreateTransportationAddressRequest;
import com.cplerings.core.api.transport.request.data.TransportationAddressData;
import com.cplerings.core.api.transport.response.CreateTransportationAddressResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.TransportOrderTestHelper;

public class CreateTransportationAddressUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TransportOrderTestHelper transportOrderTestHelper;

    @Test
    void givenCustomer_whenCreateAddress() {
        transportOrderTestHelper.createTransportOrder();
        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        CreateTransportationAddressRequest request = CreateTransportationAddressRequest.builder()
                .customerId(1L)
                .receiverPhone("test")
                .districtCode(1)
                .address("test")
                .district("test")
                .receiverName("test")
                .wardCode(1L)
                .ward("test")
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.ADDRESS_PATH)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportAddress(response);
    }

    private void thenResponseReturnTransportAddress(WebTestClient.ResponseSpec response) {
        final CreateTransportationAddressResponse responseBody = response.expectBody(CreateTransportationAddressResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final TransportationAddressData transportationOrderAddressData = responseBody.getData();
        assertThat(transportationOrderAddressData).isNotNull();
        assertThat(transportationOrderAddressData.transportationAddress()).isNotNull();
    }
}
