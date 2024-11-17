package com.cplerings.core.test.integration.crafting;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.crafting.request.CompleteCraftingStageRequest;
import com.cplerings.core.api.crafting.response.CompleteCraftingStageResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode;
import com.cplerings.core.application.crafting.input.data.RingMaintenance;
import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;
import com.cplerings.core.application.shared.entity.file.AImage;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.CraftingStageRepository;
import com.cplerings.core.infrastructure.repository.CustomOrderRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.design.CustomDesignSpouse;
import com.cplerings.core.test.shared.design.CustomDesignTestHelper;
import com.cplerings.core.test.shared.helper.BranchTestHelper;
import com.cplerings.core.test.shared.helper.DocumentTestHelper;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

class CompleteCraftingStageUseCaseIT extends AbstractIT {

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CraftingStageRepository craftingStageRepository;

    @Autowired
    private CustomOrderRepository customOrderRepository;

    @Autowired
    private BranchTestHelper branchTestHelper;

    @Autowired
    private CustomDesignTestHelper customDesignTestHelper;

    @Autowired
    private DocumentTestHelper documentTestHelper;

    private CraftingStage firstCraftingStage;

    private CraftingStage secondCraftingStage;

    private CustomOrder customOrder;

    private Collection<Long> ringIds;

    @BeforeEach
    public void setUpCraftingStage() {
        CustomDesignSpouse customDesignsAndSpouses = customDesignTestHelper.createCustomDesignsAndSpouses();
        Contract contract = Contract.builder()
                .build();
        contract = testDataSource.save(contract);
        final Branch branch = branchTestHelper.createBranch();
        Ring firstRing = Ring.builder()
                .status(RingStatus.NOT_AVAIL)
                .branch(branch)
                .spouse(customDesignsAndSpouses.spouses()[0])
                .customDesign(customDesignsAndSpouses.customDesign().get(0))
                .build();
        firstRing = testDataSource.save(firstRing);
        Ring secondRing = Ring.builder()
                .status(RingStatus.NOT_AVAIL)
                .branch(branch)
                .spouse(customDesignsAndSpouses.spouses()[1])
                .customDesign(customDesignsAndSpouses.customDesign().get(1))
                .build();
        secondRing = testDataSource.save(secondRing);
        this.ringIds = Set.of(firstRing.getId(), secondRing.getId());

        CustomOrder customOrder = CustomOrder.builder()
                .customer(accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL).orElse(null))
                .contract(contract)
                .status(CustomOrderStatus.PENDING)
                .totalPrice(Money.create(BigDecimal.valueOf(100000)))
                .firstRing(firstRing)
                .secondRing(secondRing)
                .build();
        customOrder = testDataSource.save(customOrder);
        this.customOrder = customOrder;

        CraftingStage firstCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PAID)
                .name("Stage 1")
                .progress(30)
                .build();
        this.firstCraftingStage = testDataSource.save(firstCraftingStage);
        CraftingStage secondCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PAID)
                .name("Stage 2")
                .progress(100)
                .build();
        this.secondCraftingStage = testDataSource.save(secondCraftingStage);
    }

    @Test
    void givenJeweler_whenCompleteCraftingStage() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.JEWELER_EMAIL);
        final CompleteCraftingStageRequest request = CompleteCraftingStageRequest.builder()
                .imageId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.COMPLETE_CRAFTING_STAGE_PATH, firstCraftingStage.getId())
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenResponseIsCompletedCraftingStage(response);
        thenCraftingStageIsDone(firstCraftingStage.getId());
    }

    @Test
    void givenJeweler_whenCompleteFinalCraftingStage() {
        final CraftingStage updatedFirstCraftingStage = craftingStageRepository.findById(firstCraftingStage.getId())
                .orElse(null);
        assertThat(updatedFirstCraftingStage).isNotNull();
        updatedFirstCraftingStage.setCompletionDate(TemporalUtils.getCurrentInstantUTC());
        testDataSource.save(updatedFirstCraftingStage);

        final Document firstMaintenance = documentTestHelper.createDocument();
        final RingMaintenance firstRingMaintenance = RingMaintenance.builder()
                .ringId(ringIds.stream()
                        .min(Comparator.naturalOrder())
                        .orElseThrow(() -> new IllegalStateException("No ring ID detected")))
                .maintenanceDocumentId(firstMaintenance.getId())
                .build();
        final Document secondMaintenance = documentTestHelper.createDocument();
        final RingMaintenance secondRingMaintenance = RingMaintenance.builder()
                .ringId(ringIds.stream()
                        .max(Comparator.naturalOrder())
                        .orElseThrow(() -> new IllegalStateException("No ring ID detected")))
                .maintenanceDocumentId(secondMaintenance.getId())
                .build();

        final String token = jwtTestHelper.generateToken(AccountTestConstant.JEWELER_EMAIL);
        final CompleteCraftingStageRequest request = CompleteCraftingStageRequest.builder()
                .imageId(1L)
                .ringMaintenances(Set.of(firstRingMaintenance, secondRingMaintenance))
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.COMPLETE_CRAFTING_STAGE_PATH, secondCraftingStage.getId())
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenResponseIsCompletedCraftingStage(response);
        thenCraftingStageIsDone(firstCraftingStage.getId());
        thenCustomOrderIsDone();
        thenRingsHaveMaintenanceDocuments();
    }

    @Test
    void givenJeweler_whenCompleteCraftingStageButPreviousAreNotCompleted() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.JEWELER_EMAIL);
        final CompleteCraftingStageRequest request = CompleteCraftingStageRequest.builder()
                .imageId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.COMPLETE_CRAFTING_STAGE_PATH, secondCraftingStage.getId())
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsBadRequestWithErrorCode(response, CompleteCraftingStageErrorCode.PREVIOUS_CRAFTING_STAGE_NOT_COMPLETED);
    }

    @Test
    void givenJeweler_whenCompleteCraftingStageButPreviousAreNotPaid() {
        final CraftingStage updatedFirstCraftingStage = craftingStageRepository.findById(firstCraftingStage.getId())
                .orElse(null);
        assertThat(updatedFirstCraftingStage).isNotNull();
        updatedFirstCraftingStage.setStatus(CraftingStageStatus.PENDING);
        testDataSource.save(updatedFirstCraftingStage);

        final String token = jwtTestHelper.generateToken(AccountTestConstant.JEWELER_EMAIL);
        final CompleteCraftingStageRequest request = CompleteCraftingStageRequest.builder()
                .imageId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.COMPLETE_CRAFTING_STAGE_PATH, secondCraftingStage.getId())
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsBadRequestWithErrorCode(response, CompleteCraftingStageErrorCode.PREVIOUS_CRAFTING_STAGE_NOT_PAID);
    }

    private void thenResponseIsCompletedCraftingStage(WebTestClient.ResponseSpec response) {
        final CompleteCraftingStageResponse responseBody = response.expectBody(CompleteCraftingStageResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(ACraftingStage.class);

        final ACraftingStage data = responseBody.getData();
        assertThat(data).isNotNull();

        assertThat(data.getStatus()).isNotNull();
        assertThat(data.getCompletionDate()).isNotNull();
        assertThat(data.getProgress()).isNotNull();
        assertThat(data.getName()).isNotBlank();
        assertThat(data.getId()).isNotNull();
        assertThat(data.getCustomOrderId()).isNotNull();
        assertThat(data.getImage()).isNotNull();

        final AImage image = data.getImage();
        assertThat(image.getId()).isNotNull();
        assertThat(image.getUrl()).isNotBlank();
    }

    private void thenCraftingStageIsDone(Long craftingStageId) {
        final CraftingStage craftingStage = craftingStageRepository.findById(craftingStageId)
                .orElse(null);
        assertThat(craftingStage).isNotNull();
        assertThat(craftingStage.getCompletionDate()).isNotNull();
    }

    private void thenCustomOrderIsDone() {
        final CustomOrder expectedCustomOrder = customOrderRepository.findById(customOrder.getId())
                .orElse(null);
        assertThat(expectedCustomOrder).isNotNull();
        assertThat(expectedCustomOrder.getStatus()).isEqualTo(CustomOrderStatus.DONE);
    }

    private void thenRingsHaveMaintenanceDocuments() {
        final Collection<Ring> rings = testDataSource.findAllRingsByIds(ringIds);
        assertThat(rings).hasSize(2);
        rings.forEach(ring -> {
            assertThat(ring.getMaintenanceDocument()).isNotNull();
            assertThat(ring.getMaintenanceExpiredDate()).isNotNull();
        });
    }
}
