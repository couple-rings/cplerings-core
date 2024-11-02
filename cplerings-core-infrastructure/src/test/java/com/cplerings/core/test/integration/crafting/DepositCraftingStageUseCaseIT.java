package com.cplerings.core.test.integration.crafting;

import static com.cplerings.core.application.crafting.error.DepositCraftingStageErrorCode.PREVIOUS_STAGE_NOT_PAID;
import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.crafting.data.CraftingStagePaymentLinkData;
import com.cplerings.core.api.crafting.request.DepositCraftingStageRequest;
import com.cplerings.core.api.crafting.response.DepositCraftingStageResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

public class DepositCraftingStageUseCaseIT extends AbstractIT {

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private SpouseTestHelper spouseTestHelper;

    private CraftingStage firstCraftingStage;

    private CraftingStage secondCraftingStage;

    @BeforeEach
    void setUpCustomOrderAndCraftingStages() {
        final Spouse[] spouses = spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);
        Contract contract = Contract.builder()
                .build();
        contract = testDataSource.save(contract);
        Branch branch = Branch.builder()
                .address("123 Hello")
                .phone("1234567890")
                .storeName("Hello")
                .build();
        branch = testDataSource.save(branch);
        Ring firstRing = Ring.builder()
                .status(RingStatus.NOT_AVAIL)
                .branch(branch)
                .spouse(spouses[0])
                .build();
        firstRing = testDataSource.save(firstRing);
        Ring secondRing = Ring.builder()
                .status(RingStatus.NOT_AVAIL)
                .branch(branch)
                .spouse(spouses[1])
                .build();
        secondRing = testDataSource.save(secondRing);
        CustomOrder customOrder = CustomOrder.builder()
                .customer(accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL).orElse(null))
                .contract(contract)
                .status(CustomOrderStatus.PENDING)
                .totalPrice(Money.create(BigDecimal.valueOf(100000)))
                .firstRing(firstRing)
                .secondRing(secondRing)
                .build();
        customOrder = testDataSource.save(customOrder);

        CraftingStage firstCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 1")
                .progress(30)
                .build();
        this.firstCraftingStage = testDataSource.save(firstCraftingStage);
        CraftingStage secondCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 1")
                .progress(100)
                .build();
        this.secondCraftingStage = testDataSource.save(secondCraftingStage);
    }

    @Test
    void givenCustomer_whenDepositFirstCraftingStage() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DEPOSIT_CRAFTING_STAGE_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(DepositCraftingStageRequest.builder()
                        .craftingStageId(firstCraftingStage.getId())
                        .build())
                .send();

        thenResponseIsOk(response);
        thenPaymentLinkExists(response);
    }

    @Test
    void givenStaff_whenDepositSecondCraftingStageWithFirstStillPending() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DEPOSIT_CRAFTING_STAGE_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(DepositCraftingStageRequest.builder()
                        .craftingStageId(secondCraftingStage.getId())
                        .build())
                .send();

        thenResponseIsBadRequestWithErrorCode(response, PREVIOUS_STAGE_NOT_PAID);
    }

    private void thenPaymentLinkExists(WebTestClient.ResponseSpec response) {
        final DepositCraftingStageResponse responseBody = response.expectBody(DepositCraftingStageResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(CraftingStagePaymentLinkData.class);
        assertThat(responseBody.getData().paymentLink()).isNotBlank();
    }
}