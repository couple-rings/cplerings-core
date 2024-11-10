package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.request.DetermineDesignVersionRequest;
import com.cplerings.core.api.design.response.DetermineDesignVersionResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.design.request.DesignCustomRequest;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class DetermineDesignVersionUseCaseIT extends AbstractIT {

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

    @Test
    void givenCustomer_whenDetermineDesignVersion() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);

        DetermineDesignVersionRequest request = DetermineDesignVersionRequest.builder()
                .designVersionId(1L)
                .build();

        CustomRequest customRequest = CustomRequest.builder()
                .comment("abc")
                .status(CustomRequestStatus.PENDING)
                .state(State.ACTIVE)
                .customer(Account.builder().id(1L).build())
                .build();
        CustomRequest customRequestCreated = testDataSource.save(customRequest);
        DesignCustomRequest designCustomRequest = DesignCustomRequest.builder()
                .customRequest(customRequestCreated)
                .design(designRepository.getReferenceById(1L))
                .build();
        testDataSource.save(designCustomRequest);
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

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.ACCEPT_SINGLE_DESIGN_VERSION_PATH.replace("{designVersionId}", Long.toString(1)))
                .authorizationHeader(token)
                .method(RequestBuilder.Method.PUT)
                .send();

        thenResponseIsOk(response);
        thenDetermineSuccessfullyANdReturnDesignVersionData(response);
    }

    void thenDetermineSuccessfullyANdReturnDesignVersionData(WebTestClient.ResponseSpec response) {
        final DetermineDesignVersionResponse responseBody = response.expectBody(DetermineDesignVersionResponse.class)
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
        assertThat(responseBody.getData().designVersion().getIsAccepted()).isEqualTo(true);
    }
}
