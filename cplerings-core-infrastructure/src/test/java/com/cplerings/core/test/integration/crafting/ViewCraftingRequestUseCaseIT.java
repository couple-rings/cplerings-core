package com.cplerings.core.test.integration.crafting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.crafting.data.CraftingRequestData;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestRequest;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestsRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestResponse;
import com.cplerings.core.api.design.data.CustomRequestData;
import com.cplerings.core.api.design.response.ViewCustomRequestResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.infrastructure.repository.AccountRepository;
import com.cplerings.core.infrastructure.repository.DiamondSpecificationRepository;
import com.cplerings.core.infrastructure.repository.MetalSpecificationRepository;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.design.CustomDesignTestHelper;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class ViewCraftingRequestUseCaseIT extends AbstractIT {

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
    void givenAnyone_whenViewCraftingRequest() {
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
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_CRAFTING_REQUEST_PATH.replace("{craftingRequestId}", Long.toString(1L)))
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCraftingRequest(response);
    }

    private void thenResponseReturnCraftingRequest(WebTestClient.ResponseSpec response) {
        final ViewCraftingRequestResponse responseBody = response.expectBody(ViewCraftingRequestResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final com.cplerings.core.api.crafting.data.CraftingRequest craftingRequest = responseBody.getData();
        assertThat(craftingRequest).isNotNull();
    }
}
