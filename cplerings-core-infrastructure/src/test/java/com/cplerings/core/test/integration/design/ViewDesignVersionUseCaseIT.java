package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.design.response.ViewDesignVersionResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@Slf4j
public class ViewDesignVersionUseCaseIT extends AbstractIT {

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

    @Autowired
    private DesignVersionRepository designVersionRepository;

    @Test
    void givenCustomer_whenViewDesignVersionUseCase() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);

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
        log.info(designVersionRepository.findAll().stream()
                .map(DesignVersion::getId)
                .toList()
                .toString());

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_SINGLE_DESIGN_VERSION_PATH.replace("{designVersionId}", Long.toString(1)))
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenReturnDesignVersionData(response);
    }

    private void thenReturnDesignVersionData(WebTestClient.ResponseSpec response) {
        final ViewDesignVersionResponse responseBody = response.expectBody(ViewDesignVersionResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(com.cplerings.core.api.design.data.DesignVersion.class);
        assertThat(responseBody.getData().designVersion()).isNotNull();
    }
}
