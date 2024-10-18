package com.cplerings.core.test.integration.spouse;

import static com.cplerings.core.api.shared.AbstractResponse.Type.INFO;
import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.spouse.request.CreateSpouseRequest;
import com.cplerings.core.api.spouse.request.PrimarySpouse;
import com.cplerings.core.api.spouse.request.SecondarySpouse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;
import com.cplerings.core.infrastructure.repository.SpouseAccountRepository;
import com.cplerings.core.infrastructure.repository.SpouseRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.spouse.SpouseTestConstant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import jakarta.transaction.Transactional;

import java.time.Instant;

class CreateSpouseUseCaseIT extends AbstractIT {

    private static final String PRIMARY_SPOUSE_NAME = "John Doe";
    private static final String SECONDARY_SPOUSE_NAME = "Jane Doe";

    @Autowired
    private SpouseRepository spouseRepository;

    @Autowired
    private SpouseAccountRepository spouseAccountRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    @Transactional
    void givenSystem_whenCreateSpouse() {
        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final CreateSpouseRequest request = CreateSpouseRequest.builder()
                .primarySpouse(PrimarySpouse.builder()
                        .citizenId(SpouseTestConstant.PRIMARY_SPOUSE_CITIZEN_ID)
                        .dateOfBirth(Instant.now())
                        .fullName(PRIMARY_SPOUSE_NAME)
                        .customerId(1L)
                        .build())
                .secondarySpouse(SecondarySpouse.builder()
                        .citizenId(SpouseTestConstant.SECONDARY_SPOUSE_CITIZEN_ID)
                        .dateOfBirth(Instant.now())
                        .fullName(SECONDARY_SPOUSE_NAME)
                        .build())
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SPOUSES_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenSpousesAreCreated(response);
    }

    private void thenSpousesAreCreated(WebTestClient.ResponseSpec response) {
        final NoResponse createSpouseResponse = response.expectBody(NoResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(createSpouseResponse).isNotNull();
        assertThat(createSpouseResponse.getType()).isEqualTo(INFO);

        // Check primary Spouse
        Spouse primarySpouse = spouseRepository.findByCitizenId(SpouseTestConstant.PRIMARY_SPOUSE_CITIZEN_ID)
                .orElse(null);
        assertThat(primarySpouse).isNotNull();

        // Check secondary Spouse
        Spouse secondarySpouse = spouseRepository.findByCitizenId(SpouseTestConstant.SECONDARY_SPOUSE_CITIZEN_ID)
                .orElse(null);
        assertThat(secondarySpouse).isNotNull();

        // Check Spouse Account
        SpouseAccount spouseAccount = spouseAccountRepository.findSpouseAccountBySpouseCitizenId(SpouseTestConstant.PRIMARY_SPOUSE_CITIZEN_ID)
                .orElse(null);
        assertThat(spouseAccount).isNotNull();
        assertThat(spouseAccount.getCustomer()).isNotNull();
        assertThat(spouseAccount.getCustomer().getEmail()).isEqualTo(AccountTestConstant.CUSTOMER_EMAIL);
    }
}
