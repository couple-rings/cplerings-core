package com.cplerings.core.test.integration.craftingrequest;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.craftingrequest.data.CraftingRequest;
import com.cplerings.core.api.craftingrequest.request.CreateCraftingRequestRequest;
import com.cplerings.core.api.craftingrequest.response.CreateCraftingRequestResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.shared.valueobject.Weight;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.CustomDesignRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DesignVersionRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.infrastructure.repository.SpouseRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CreateCraftingRequestIT extends AbstractIT {

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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomDesignRepository customDesignRepository;

    private Spouse spouseCreated;
    private DesignVersion designVersionCreated;

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
        spouseCreated = spouseRepository.saveAndFlush(spouse);

        DesignVersion designVersion = DesignVersion.builder()
                .designFile(documentRepository.findById(1L).get())
                .customer(accountRepository.getReferenceById(1L))
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
        designVersionCreated = designVersionRepository.saveAndFlush(designVersion);
    }

    @Test
    void givenCustomer_whenCreateCraftingRequestUseCase() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);

        CustomDesign customDesign = CustomDesign.builder()
                .metalWeight(Weight.create(BigDecimal.valueOf(1)))
                .spouse(spouseCreated)
                .designVersion(designVersionCreated)
                .sideDiamondsCount(1)
                .account(accountRepository.findById(1L).get())
                .createdBy("Test")
                .createdAt(Instant.now())
                .modifiedAt(Instant.now())
                .modifiedBy("Test")
                .build();
        customDesignRepository.save(customDesign);

        CreateCraftingRequestRequest request = CreateCraftingRequestRequest.builder()
                .customDesignId(1L)
                .customerId(1L)
                .diamondSpecId(1L)
                .fingerSize(23)
                .engraving("test")
                .metalSpecId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CRAFTING_REQUEST_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenCreateSuccessfullyAndReturnCraftingRequestData(response);
    }

    private void thenCreateSuccessfullyAndReturnCraftingRequestData(WebTestClient.ResponseSpec response) {
        final CreateCraftingRequestResponse responseBody = response.expectBody(CreateCraftingRequestResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(CraftingRequest.class);
        assertThat(responseBody.getData().craftingRequest()).isNotNull();
    }
}
