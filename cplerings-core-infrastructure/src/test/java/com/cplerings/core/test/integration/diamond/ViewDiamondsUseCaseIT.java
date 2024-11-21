package com.cplerings.core.test.integration.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.diamond.data.DiamondsData;
import com.cplerings.core.api.diamond.request.ViewDiamondsRequest;
import com.cplerings.core.api.diamond.response.ViewDiamondsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.DiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class ViewDiamondsUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private DiamondSpecificationRepository diamondSpecificationRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenAnyone_whenViewDiamondSpecifications() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.MANAGER_EMAIL);
        ViewDiamondsRequest request = ViewDiamondsRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        Diamond diamond = Diamond.builder()
                .diamondSpecification(diamondSpecificationRepository.getReferenceById(1L))
                .branch(branchRepository.getReferenceById(1L))
                .giaDocument(documentRepository.getReferenceById(1L))
                .giaReportNumber("test")
                .build();
        testDataSource.save(diamond);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DIAMONDS_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnDiamonds(response);
    }

    private void thenResponseReturnDiamonds(WebTestClient.ResponseSpec response) {
        final ViewDiamondsResponse responseBody = response.expectBody(ViewDiamondsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final DiamondsData diamondsData = responseBody.getData();
        assertThat(diamondsData).isNotNull();
        assertThat(diamondsData.getPage()).isZero();
        assertThat(diamondsData.getPageSize()).isEqualTo(1);
        assertThat(diamondsData.getItems()).hasSize(1);
    }
}
