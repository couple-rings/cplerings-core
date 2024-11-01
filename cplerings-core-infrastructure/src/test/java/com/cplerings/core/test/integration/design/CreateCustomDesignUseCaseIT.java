package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.data.CustomDesign;
import com.cplerings.core.api.design.request.CreateCustomDesignRequest;
import com.cplerings.core.api.design.response.CreateCustomDesignResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.infrastructure.repository.SpouseRepository;
import com.cplerings.core.infrastructure.service.email.EmailServiceImpl;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.icegreen.greenmail.util.GreenMail;

class CreateCustomDesignUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private SpouseRepository spouseRepository;

    @Autowired
    private DesignVersionRepository designVersionRepository;

    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ImageRepository imageRepository;

    private Spouse spouseCreated;

    @BeforeEach
    public void start() {
        Spouse spouse = Spouse.builder()
                .createdAt(Instant.now())
                .createdBy("CP")
                .state(State.ACTIVE)
                .citizenId("07428328")
                .dateOfBirth(Instant.now())
                .fullName("test")
                .modifiedAt(Instant.now())
                .coupleId(UUID.randomUUID())
                .modifiedBy("CP")
                .build();
        spouseCreated =spouseRepository.saveAndFlush(spouse);

        DesignVersion designVersion = DesignVersion.builder()
                .designFile(documentRepository.findById(1L).get())
                .image(imageRepository.findById(1L).get())
                .design(designRepository.findById(1L).get())
                .versionNumber(3)
                .isAccepted(false)
                .isOld(false)
                .createdAt(Instant.now())
                .createdBy("CP")
                .modifiedAt(Instant.now())
                .modifiedBy("CP")
                .build();
        designVersionRepository.saveAndFlush(designVersion);
    }

    @Disabled
    @Test
    void givenStaff_whenCreateCustomDesignUseCase() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);

        CreateCustomDesignRequest request = CreateCustomDesignRequest.builder()
                .customerId(1L)
                .designVersionId(1L)
                .blueprint("test")
                .spouseId(spouseCreated.getId())
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
                .isExactlyInstanceOf(CustomDesign.class);
        assertThat(responseBody.getData().customDesign()).isNotNull();
    }
}
