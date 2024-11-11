package com.cplerings.core.test.integration.crafting;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.crafting.data.CraftingRequestData;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestsRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestsResponse;
import com.cplerings.core.api.design.data.CustomRequestsData;
import com.cplerings.core.api.design.response.ViewCustomRequestsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.shared.valueobject.Weight;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DesignRepository;
import com.cplerings.core.infrastructure.repository.DiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.DocumentRepository;
import com.cplerings.core.infrastructure.repository.ImageRepository;
import com.cplerings.core.infrastructure.repository.MetalSpecificationRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.design.CustomDesignTestHelper;
import com.cplerings.core.test.shared.design.DesignVersionTestHelper;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

public class ViewCraftingRequestsUseCaseIT extends AbstractIT{

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

    @Test
    void givenAnyone_whenViewCraftingRequests() {
        CustomDesign customDesign = customDesignTestHelper.createCustomDesign();
        CraftingRequest craftingRequest = CraftingRequest.builder()
                .customDesign(customDesign)
                .craftingRequestStatus(CraftingRequestStatus.PENDING)
                .fingerSize(12)
                .customer(accountRepository.getReferenceById(1L))
                .metalSpecification(metalSpecificationRepository.getReferenceById(1L))
                .diamondSpecification(diamondSpecificationRepository.getReferenceById(1L))
                .engraving("test")
                .build();
        testDataSource.save(craftingRequest);
        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        ViewCraftingRequestsRequest request = ViewCraftingRequestsRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_CRAFTING_REQUESTS_PATH)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCraftingRequests(response);

    }

    private void thenResponseReturnCraftingRequests(WebTestClient.ResponseSpec response) {
        final ViewCraftingRequestsResponse responseBody = response.expectBody(ViewCraftingRequestsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final CraftingRequestData craftingRequestData = responseBody.getData();
        assertThat(craftingRequestData).isNotNull();
        assertThat(craftingRequestData.getPage()).isZero();
        assertThat(craftingRequestData.getPageSize()).isEqualTo(1);
        assertThat(craftingRequestData.getItems()).hasSize(1);
        assertThat(craftingRequestData.getTotalPages()).isEqualTo(1);
    }
}
