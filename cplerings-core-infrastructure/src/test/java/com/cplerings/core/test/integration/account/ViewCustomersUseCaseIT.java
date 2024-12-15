package com.cplerings.core.test.integration.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.account.data.CustomersData;
import com.cplerings.core.api.account.request.ViewCustomersRequest;
import com.cplerings.core.api.account.response.ViewCustomersResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class ViewCustomersUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenStaff_whenViewJewelers() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        ViewCustomersRequest request = ViewCustomersRequest.builder()
                .page(0)
                .pageSize(1)
                .email("customer")
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CUSTOMERS_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenReturnCustomers(response);
    }

    private void thenReturnCustomers(WebTestClient.ResponseSpec response) {
        final ViewCustomersResponse responseBody = response.expectBody(ViewCustomersResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final CustomersData customersData = responseBody.getData();
        assertThat(customersData).isNotNull();
        assertThat(customersData.getPage()).isZero();
        assertThat(customersData.getPageSize()).isEqualTo(1);
        assertThat(customersData.getItems()).hasSize(1);
    }
}
