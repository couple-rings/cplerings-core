package com.cplerings.core.test.integration.jewelry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.jewelry.request.CreateJewelryRequest;
import com.cplerings.core.api.jewelry.response.CreateJewelryResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.DiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class CreateJewelryUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DiamondSpecificationRepository diamondSpecificationRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    void givenManager_whenCreateDesignUseCase() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);
        CreateJewelryRequest request = CreateJewelryRequest.builder()
                .designId(1L)
                .branchId(1L)
                .diamondId(1L)
                .metalSpecId(1L)
                .build();
        Diamond diamond = Diamond.builder()
                .diamondSpecification(diamondSpecificationRepository.getReferenceById(1L))
                .branch(branchRepository.getReferenceById(1L))
                .giaDocument(documentRepository.getReferenceById(1L))
                .giaReportNumber("test")
                .build();
        testDataSource.save(diamond);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.JEWELRIES_PATH)
                .authorizationHeader(token)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenCreateSuccessfullyAndReturnDesign(response);
    }

    private void thenCreateSuccessfullyAndReturnDesign(WebTestClient.ResponseSpec response) {
        final CreateJewelryResponse responseBody = response.expectBody(CreateJewelryResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(AJewelry.class);
        assertThat(responseBody.getData().getDesign()).isNotNull();
    }
}
