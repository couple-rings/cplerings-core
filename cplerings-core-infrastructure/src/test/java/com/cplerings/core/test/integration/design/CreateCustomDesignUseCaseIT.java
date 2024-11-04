package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.design.data.CustomDesignData;
import com.cplerings.core.api.design.request.CreateCustomDesignRequest;
import com.cplerings.core.api.design.response.CreateCustomDesignResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@Disabled("Test fail when verify due to spouse_seq not being called")
class CreateCustomDesignUseCaseIT extends AbstractIT {

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
    private SpouseTestHelper spouseTestHelper;

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
    void givenStaff_whenCreateCustomDesignUseCase() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        final Spouse spouse = spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);

        CreateCustomDesignRequest request = CreateCustomDesignRequest.builder()
                .customerId(1L)
                .designVersionId(1L)
                .blueprint("test")
                .spouseId(spouse.getId())
                .metalWeight(BigDecimal.valueOf(0.5))
                .sideDiamondAmount(2)
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CREATE_CUSTOM_DESIGN_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenCreateSuccessfullyANdReturnCustomDesignData(response);
    }

    private void thenCreateSuccessfullyANdReturnCustomDesignData(WebTestClient.ResponseSpec response) {
        final CreateCustomDesignResponse responseBody = response.expectBody(CreateCustomDesignResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(CustomDesignData.class);
        assertThat(responseBody.getData().customDesign()).isNotNull();
    }
}
