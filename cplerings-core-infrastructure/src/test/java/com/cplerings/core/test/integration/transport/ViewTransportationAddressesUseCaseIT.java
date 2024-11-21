package com.cplerings.core.test.integration.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.transport.data.TransportationAddressesData;
import com.cplerings.core.api.transport.request.ViewTransportationAddressesRequest;
import com.cplerings.core.api.transport.response.ViewTransportationAddressesResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class ViewTransportationAddressesUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenCustomer_whenViewTransportationAddresses() {
        var transportationAddress = TransportationAddress.builder()
                .customer(accountRepository.getReferenceById(1L))
                .receiverPhone("test")
                .districtCode("1")
                .address("test")
                .district("test")
                .receiverName("test")
                .wardCode("1")
                .ward("test")
                .build();
        testDataSource.save(transportationAddress);
        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        ViewTransportationAddressesRequest request = ViewTransportationAddressesRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.ADDRESS_PATH)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnTransportationAddresses(response);
    }

    private void thenResponseReturnTransportationAddresses(WebTestClient.ResponseSpec response) {
        final ViewTransportationAddressesResponse responseBody = response.expectBody(ViewTransportationAddressesResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final TransportationAddressesData transportationAddressesData = responseBody.getData();
        assertThat(transportationAddressesData).isNotNull();
        assertThat(transportationAddressesData.getPage()).isZero();
        assertThat(transportationAddressesData.getPageSize()).isEqualTo(1);
        assertThat(transportationAddressesData.getItems()).hasSize(1);
        assertThat(transportationAddressesData.getTotalPages()).isEqualTo(1);
    }
}
