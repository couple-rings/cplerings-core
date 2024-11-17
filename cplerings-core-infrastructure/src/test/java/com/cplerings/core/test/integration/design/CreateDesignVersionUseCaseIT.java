package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import com.cplerings.core.api.design.data.CreateDesignVersionData;
import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.design.request.CreateDesignVersionRequest;
import com.cplerings.core.api.design.request.data.CreateDesignVersionRequestData;
import com.cplerings.core.api.design.response.CreateDesignVersionResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class CreateDesignVersionUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenStaff_whenCreateDesignVersionUseCase() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        DesignSession designSession = DesignSession.builder()
                .customer(accountRepository.getReferenceById(1L))
                .status(DesignSessionStatus.UNUSED)
                .sessionId(UUID.randomUUID())
                .build();
        testDataSource.save(designSession);
        CreateDesignVersionRequestData femaleVersionsRequestData = CreateDesignVersionRequestData.builder()
                .previewImageId(1L)
                .designFileId(1L)
                .customerId(1L)
                .designId(1L)
                .build();
        CreateDesignVersionRequestData maleVersionsRequestData = CreateDesignVersionRequestData.builder()
                .previewImageId(11L)
                .designFileId(11L)
                .customerId(1L)
                .designId(11L)
                .build();
        CreateDesignVersionRequest request = CreateDesignVersionRequest.builder()
                .femaleVersion(femaleVersionsRequestData)
                .maleVersion(maleVersionsRequestData)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DESIGN_VERSION_PATH)
                .authorizationHeader(token)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenCreateSuccessfullyANdReturnDesignVersionData(response);
    }

    private void thenCreateSuccessfullyANdReturnDesignVersionData(WebTestClient.ResponseSpec response) {
        final CreateDesignVersionResponse responseBody = response.expectBody(CreateDesignVersionResponse.class)
                .returnResult()
                .getResponseBody();
        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(CreateDesignVersionData.class);
        assertThat(responseBody.getData().firstDesignVersion()).isNotNull();
        assertThat(responseBody.getData().secondDesignVersion()).isNotNull();
    }
}
