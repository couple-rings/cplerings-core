package com.cplerings.core.test.integration.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.diamond.request.data.UpdateDiamondRequestData;
import com.cplerings.core.api.diamond.response.UpdateDiamondResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.DiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class UpdateDiamondUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private DiamondSpecificationRepository diamondSpecificationRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    void givenManager_whenUpdateDiamond() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);
        Diamond diamond = Diamond.builder()
                .diamondSpecification(diamondSpecificationRepository.getReferenceById(1L))
                .branch(branchRepository.getReferenceById(1L))
                .giaDocument(documentRepository.getReferenceById(1L))
                .giaReportNumber("test")
                .build();
        Diamond diamondCreated = testDataSource.save(diamond);
        final UpdateDiamondRequestData request = UpdateDiamondRequestData.builder()
                .giaReportNumber("testtest")
                .diamondSpecificationId(diamond.getDiamondSpecification().getId())
                .giaDocumentId(diamond.getGiaDocument().getId())
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SINGLE_DIAMOND_PATH, diamondCreated.getId())
                .method(RequestBuilder.Method.PUT)
                .authorizationHeader(token)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenDiamondIsUpdated(response);
    }

    private void thenDiamondIsUpdated(WebTestClient.ResponseSpec response) {
        final UpdateDiamondResponse responseBody = response.expectBody(UpdateDiamondResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final ADiamond diamond = responseBody.getData();
        assertThat(diamond).isNotNull();
        assertThat(diamond.getId()).isNotNull();
        assertThat(diamond.getGiaReportNumber()).isNotBlank();
        assertThat(diamond.getGiaReportNumber()).isEqualTo("testtest");
    }
}
