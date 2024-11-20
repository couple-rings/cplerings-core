package com.cplerings.core.test.integration.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.diamond.request.CreateDiamondRequest;
import com.cplerings.core.api.diamond.response.CreateDiamondResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.BranchTestHelper;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class CreateDiamondUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private BranchTestHelper branchTestHelper;

    @Test
    void givenManager_whenCreateDiamond() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);
        final CreateDiamondRequest request = CreateDiamondRequest.builder()
                .diamondSpecificationId(1L)
                .branchId(branchTestHelper.createBranch().getId())
                .giaReportNumber("123456")
                .giaDocumentId(1L)
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DIAMONDS_PATH)
                .method(RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .query(request)
                .send();

        thenResponseIsOk(response);
        thenDiamondDataIsReturned(response);
        thenDiamondIsCreated(response);
    }

    private void thenDiamondDataIsReturned(WebTestClient.ResponseSpec response) {

    }

    private void thenDiamondIsCreated(WebTestClient.ResponseSpec response) {
        final CreateDiamondResponse responseBody = response.expectBody(CreateDiamondResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final ADiamond diamond = responseBody.getData();
        assertThat(diamond).isNotNull();
        assertThat(diamond.getId()).isNotNull();
        assertThat(diamond.getCreatedAt()).isNotNull();
        assertThat(diamond.getDiamondSpecification()).isNotNull();
        assertThat(diamond.getBranch()).isNotNull();
        assertThat(diamond.getGiaReportNumber()).isNotBlank();
    }
}
