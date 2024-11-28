package com.cplerings.core.test.integration.spouse;

import static com.cplerings.core.api.shared.AbstractResponse.Type.DATA;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.spouse.request.ViewSpousesOfCustomerRequest;
import com.cplerings.core.api.spouse.response.ViewSpousesOfCustomerResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

public class ViewSpousesOfCustomerUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private SpouseTestHelper spouseTestHelper;

    @Test
    void givenStaff_whenViewSpousesOfACustomer() {
        spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);

        String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        ViewSpousesOfCustomerRequest request = ViewSpousesOfCustomerRequest.builder()
                .customerId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SPOUSES_PATH)
                .authorizationHeader(token)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenReturnSpousesData(response);
    }

    private void thenReturnSpousesData(WebTestClient.ResponseSpec response) {
        final ViewSpousesOfCustomerResponse spouseResponse = response.expectBody(ViewSpousesOfCustomerResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(spouseResponse).isNotNull();
        assertThat(spouseResponse.getType()).isEqualTo(DATA);

        assertThat(spouseResponse.getData()).isNotNull();
        assertThat(spouseResponse.getData().spouses()).hasSize(2);
        assertThat(spouseResponse.getData().spouses().get(0)).isNotNull();
        assertThat(spouseResponse.getData().spouses().get(1)).isNotNull();
        assertThat(spouseResponse.getData().spouses().get(1).getFullName()).isNotBlank();
    }
}
