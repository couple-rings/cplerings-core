package com.cplerings.core.test.integration.spouse;

import static com.cplerings.core.api.shared.AbstractResponse.Type.INFO;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.authentication.request.LoginCredentialRequest;
import com.cplerings.core.api.authentication.response.AuthenticationTokenResponse;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.api.spouse.request.CreateSpouseRequest;
import com.cplerings.core.api.spouse.request.PrimarySpouse;
import com.cplerings.core.api.spouse.request.SecondarySpouse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;
import com.cplerings.core.infrastructure.datasource.spouseaccount.SharedSpouseAccountDataSource;
import com.cplerings.core.infrastructure.repository.SpouseAccountRepository;
import com.cplerings.core.infrastructure.repository.SpouseRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;

class CreateSpouseIT extends AbstractIT {

    @Autowired
    private SpouseRepository spouseRepository;

    @Autowired
    private SpouseAccountRepository spouseAccountRepository;

    @Autowired
    private SharedSpouseAccountDataSource sharedSpouseAccountDataSource;

    @Test
    void givenSystem_whenCreateSpouse() {
        // Authenticate
        final WebTestClient.ResponseSpec responseAuth = requestBuilder()
                .path(APIConstant.LOGIN_PATH)
                .method(RequestBuilder.Method.POST)
                .body(new LoginCredentialRequest(AccountTestConstant.CUSTOMER_EMAIL, AccountTestConstant.PASSWORD))
                .send();

        final AuthenticationTokenResponse responseBodyAuth = responseAuth.expectBody(com.cplerings.core.api.authentication.response.AuthenticationTokenResponse.class)
                .returnResult()
                .getResponseBody();

        // Arrange
        final CreateSpouseRequest request = CreateSpouseRequest.builder()
                .primarySpouse(PrimarySpouse.builder()
                        .citizenId("07425517362")
                        .dateOfBirth(Instant.now())
                        .fullName("string")
                        .customerId(1L)
                        .build())
                .secondarySpouse(SecondarySpouse.builder()
                        .citizenId("07425517323")
                        .dateOfBirth(Instant.now())
                        .fullName("string2")
                        .build())
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SPOUSES_PATH)
                .authorizationHeader(responseBodyAuth.getData().token())
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
    }

    private void thenExistNewSpouses(WebTestClient.ResponseSpec response, String primarySpouseCitizenId, String secondarySpouseCitizenId, Long accountId) {
        final NoResponse createSpouseResponse = response.expectBody(NoResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(createSpouseResponse.getType()).isEqualTo(INFO);

        // Check primary Spouse
        Spouse primarySpouse = spouseRepository.findByCitizenId(primarySpouseCitizenId)
                .orElse(null);
        assertThat(primarySpouse).isNotNull();
        assertThat(primarySpouse.getCitizenId()).isEqualTo(primarySpouseCitizenId);

        // Check secondary Spouse
        Spouse secondarySpouse = spouseRepository.findByCitizenId(secondarySpouseCitizenId)
                .orElse(null);
        assertThat(secondarySpouse).isNotNull();
        assertThat(secondarySpouse.getCitizenId()).isEqualTo(secondarySpouseCitizenId);

        // Check Spouse Account
        SpouseAccount spouseAccount = sharedSpouseAccountDataSource.findBySpouseAndCustomer(primarySpouse.getId(), accountId)
                .orElse(null);
        assertThat(spouseAccount).isNotNull();
        assertThat(spouseAccount.getSpouse().getCitizenId()).isEqualTo(primarySpouseCitizenId);
        assertThat(spouseAccount.getCustomer().getId()).isEqualTo(accountId);
    }
}
