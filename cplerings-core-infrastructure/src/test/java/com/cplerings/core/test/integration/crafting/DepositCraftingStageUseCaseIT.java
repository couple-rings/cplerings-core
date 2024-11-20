package com.cplerings.core.test.integration.crafting;

import static com.cplerings.core.application.crafting.error.DepositCraftingStageErrorCode.PREVIOUS_STAGE_NOT_PAID;
import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.crafting.data.CraftingStagePaymentLinkData;
import com.cplerings.core.api.crafting.request.DepositCraftingStageRequest;
import com.cplerings.core.api.crafting.response.DepositCraftingStageResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.CraftingStageRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.CustomOrderTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

public class DepositCraftingStageUseCaseIT extends AbstractIT {

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CraftingStageRepository craftingStageRepository;

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    private CraftingStage firstCraftingStage;

    private CraftingStage secondCraftingStage;

    @BeforeEach
    void setUpCustomOrderAndCraftingStages() {
        CustomOrder customOrder = customOrderTestHelper.createCustomOrder();

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
                .name("Stage 2")
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
    void givenCustomer_whenDepositFinalCraftingStageWithTransportationAddress() {
        final CraftingStage firstCraftingStagePaid = craftingStageRepository.findById(this.firstCraftingStage.getId())
                .orElse(null);
        assertThat(firstCraftingStagePaid).isNotNull();
        firstCraftingStagePaid.setStatus(CraftingStageStatus.PAID);
        testDataSource.save(firstCraftingStagePaid);

        TransportationAddress transportationAddress = TransportationAddress.builder()
                .address("123 Hello")
                .customer(accountRepository.findByEmail(AccountTestConstant.CUSTOMER_EMAIL).orElse(null))
                .districtCode("01")
                .district("District 1")
                .wardCode("02")
                .ward("Ward 1")
                .receiverName("John Doe")
                .receiverPhone("1234567890")
                .build();
        transportationAddress = testDataSource.save(transportationAddress);

        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DEPOSIT_CRAFTING_STAGE_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(DepositCraftingStageRequest.builder()
                        .craftingStageId(secondCraftingStage.getId())
                        .transportationAddressId(transportationAddress.getId())
                        .build())
                .send();

        thenResponseIsOk(response);
        thenPaymentLinkExists(response);
        thenTransportationAddressIsAdded(secondCraftingStage.getId());
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

    private void thenTransportationAddressIsAdded(Long craftingStageId) {
        final CraftingStage craftingStage = testDataSource.findCraftingStageById(craftingStageId)
                .orElse(null);
        assertThat(craftingStage).isNotNull();
        final CustomOrder customOrder = craftingStage.getCustomOrder();
        assertThat(customOrder).isNotNull();
        assertThat(customOrder.getTransportationAddress()).isNotNull();
    }
}
