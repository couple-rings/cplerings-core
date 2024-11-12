package com.cplerings.core.test.integration.contract;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.contract.request.DetermineContractRequest;
import com.cplerings.core.api.contract.request.data.DetermineContractRequestData;
import com.cplerings.core.api.contract.response.DetermineContractResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class DetermineContractUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenCustomer_whenSignTheContract() {
        Contract contract = Contract.builder()
                .build();
        testDataSource.save(contract);
        DetermineContractRequestData request = DetermineContractRequestData.builder()
                .signedDate(TemporalUtils.getCurrentInstantUTC())
                .documentId(1L)
                .signatureId(1L)
                .build();
        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SIGNING_CONTRACT_PATH.replace("{contractId}", Long.toString(1L)))
                .method(AbstractIT.RequestBuilder.Method.PUT)
                .authorizationHeader(token)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnContractData(response);
    }

    private void thenResponseReturnContractData(WebTestClient.ResponseSpec response) {
        final DetermineContractResponse responseBody = response.expectBody(DetermineContractResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final com.cplerings.core.api.contract.data.Contract contract = responseBody.getData();
        assertThat(contract).isNotNull();
        assertThat(contract.contract().getDocument()).isNotNull();
        assertThat(contract.contract().getSignedDate()).isNotNull();
        assertThat(contract.contract().getSignature()).isNotNull();
    }
}
