package com.cplerings.core.test.integration.spouse;

import static com.cplerings.core.api.shared.AbstractResponse.Type.DATA;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.spouse.response.VerifyResidentIdResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.spouse.SpouseTestConstant;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

class VerifyResidentIdUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private SpouseTestHelper spouseTestHelper;

    @Test
    void givenStaff_whenViewSpousesOfACustomer() {
        spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);

        String token = jwtTestHelper.generateToken(AccountTestConstant.TRANSPORTER_EMAIL);
        String citizenId = SpouseTestConstant.PRIMARY_SPOUSE_CITIZEN_ID;
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VERIFY_SPOUSE_PATH, citizenId)
                .authorizationHeader(token)
                .method(RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenReturnSpousesData(response);
    }

    private void thenReturnSpousesData(WebTestClient.ResponseSpec response) {
        final VerifyResidentIdResponse spouseResponse = response.expectBody(VerifyResidentIdResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(spouseResponse).isNotNull();
        assertThat(spouseResponse.getType()).isEqualTo(DATA);

        assertThat(spouseResponse.getData()).isNotNull();
        assertThat(spouseResponse.getData().spouse().getFullName()).isNotNull();
    }
}
