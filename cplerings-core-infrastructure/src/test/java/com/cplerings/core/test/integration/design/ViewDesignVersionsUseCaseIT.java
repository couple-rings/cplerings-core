package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.data.DesignVersionsData;
import com.cplerings.core.api.design.request.ViewDesignVersionsRequest;
import com.cplerings.core.api.design.response.ViewDesignVersionsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class ViewDesignVersionsUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestDataSource testDataSource;

    @BeforeEach
    public void start() {
        DesignVersion designVersion = DesignVersion.builder()
                .designFile(documentRepository.getReferenceById(1L))
                .customer(accountRepository.getReferenceById(1L))
                .image(imageRepository.getReferenceById(1L))
                .design(designRepository.getReferenceById(1L))
                .versionNumber(3)
                .isAccepted(false)
                .isOld(false)
                .build();
        testDataSource.save(designVersion);
    }

    @Test
    void givenCustomer_whenViewDesignVersions() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        ViewDesignVersionsRequest request = ViewDesignVersionsRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DESIGN_VERSION_PATH)
                .authorizationHeader(token)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnListOfDesignVersions(response);
    }

    private void thenResponseReturnListOfDesignVersions(WebTestClient.ResponseSpec response) {
        final ViewDesignVersionsResponse responseBody = response.expectBody(ViewDesignVersionsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final DesignVersionsData designVersionsData = responseBody.getData();
        assertThat(designVersionsData).isNotNull();
        assertThat(designVersionsData.getPage()).isZero();
        assertThat(designVersionsData.getPageSize()).isEqualTo(1);
        assertThat(designVersionsData.getItems()).hasSize(1);
        assertThat(designVersionsData.getTotalPages()).isEqualTo(1);
    }
}
