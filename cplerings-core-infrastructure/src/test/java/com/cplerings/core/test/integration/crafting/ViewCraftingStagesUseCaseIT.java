package com.cplerings.core.test.integration.crafting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.crafting.data.CraftingStagesData;
import com.cplerings.core.api.crafting.request.ViewCraftingStagesRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingStagesResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
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
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

class ViewCraftingStagesUseCaseIT extends AbstractIT {

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private SpouseTestHelper spouseTestHelper;

    @Autowired
    private CraftingStageRepository craftingStageRepository;

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    @Test
    void givenAnyone_whenViewCraftingStages() {
        CustomOrder customOrder = customOrderTestHelper.createCustomOrder();

        CraftingStage firstCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 1")
                .progress(30)
                .build();
        testDataSource.save(firstCraftingStage);
        CraftingStage secondCraftingStage = CraftingStage.builder()
                .customOrder(customOrder)
                .status(CraftingStageStatus.PENDING)
                .name("Stage 1")
                .progress(100)
                .build();
        testDataSource.save(secondCraftingStage);
        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        ViewCraftingStagesRequest request = ViewCraftingStagesRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CRAFTING_STAGE_PATH)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCraftingRequest(response);
    }

    private void thenResponseReturnCraftingRequest(WebTestClient.ResponseSpec response) {
        final ViewCraftingStagesResponse responseBody = response.expectBody(ViewCraftingStagesResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final CraftingStagesData craftingStages = responseBody.getData();
        assertThat(craftingStages).isNotNull();
        assertThat(craftingStages.getPage()).isZero();
        assertThat(craftingStages.getPageSize()).isEqualTo(1);
        assertThat(craftingStages.getItems()).hasSize(1);
    }
}
