package com.cplerings.core.test.integration.crafting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.crafting.data.ViewCraftingRequestsGroupsData;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestsGroupsRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestsGroupsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.BranchRepository;
import com.cplerings.core.infrastructure.repository.DiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.MetalSpecificationRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.design.CustomDesignTestHelper;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class ViewCraftingRequestsGroupsUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;


    @Autowired
    private CustomDesignTestHelper customDesignTestHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MetalSpecificationRepository metalSpecificationRepository;

    @Autowired
    private DiamondSpecificationRepository diamondSpecificationRepository;

    @Autowired
    private TestDataSource testDataSource;

    @Autowired
    private BranchRepository branchRepository;

    @Test
    void givenStaff_viewCraftingRequests() {
        CustomDesign customDesign = customDesignTestHelper.createCustomDesign();
        CraftingRequest craftingRequest = CraftingRequest.builder()
                .customDesign(customDesign)
                .craftingRequestStatus(CraftingRequestStatus.PENDING)
                .fingerSize(12)
                .customer(accountRepository.getReferenceById(1L))
                .metalSpecification(metalSpecificationRepository.getReferenceById(1L))
                .diamondSpecification(diamondSpecificationRepository.getReferenceById(1L))
                .engraving("test")
                .branch(branchRepository.getReferenceById(1L))
                .build();
        testDataSource.save(craftingRequest);
        String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        ViewCraftingRequestsGroupsRequest request = ViewCraftingRequestsGroupsRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_CRAFTING_REQUEST_GROUPS_PATH)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCraftingRequestsGroups(response);
    }

    private void thenResponseReturnCraftingRequestsGroups(WebTestClient.ResponseSpec response) {
        final ViewCraftingRequestsGroupsResponse responseBody = response.expectBody(ViewCraftingRequestsGroupsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final ViewCraftingRequestsGroupsData craftingRequestsGroupsData = responseBody.getData();
        assertThat(craftingRequestsGroupsData).isNotNull();
        assertThat(craftingRequestsGroupsData.getPage()).isZero();
        assertThat(craftingRequestsGroupsData.getPageSize()).isEqualTo(1);
        assertThat(craftingRequestsGroupsData.getItems()).hasSize(1);
    }
}
