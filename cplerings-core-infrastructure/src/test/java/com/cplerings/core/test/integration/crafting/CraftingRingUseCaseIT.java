package com.cplerings.core.test.integration.crafting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.crafting.request.CraftingRingRequest;
import com.cplerings.core.api.crafting.request.data.CraftingRingData;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.spouse.SpouseTestHelper;

class CraftingRingUseCaseIT extends AbstractIT {

    @Autowired
    private SpouseTestHelper spouseTestHelper;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenCustomer_whenCreateCraftingRequestUseCase() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);

        CraftingRingData self = CraftingRingData.builder()
                .designId(1L)
                .diamondSpecId(1L)
                .metalSpecId(1L)
                .fingerSize(7)
                .engraving("test")
                .spouseId(1L)
                .build();

        CraftingRingData partner = CraftingRingData.builder()
                .designId(11L)
                .diamondSpecId(1L)
                .metalSpecId(1L)
                .fingerSize(7)
                .engraving("test")
                .spouseId(2L)
                .build();

        spouseTestHelper.createSpouseFromCustomerEmail(AccountTestConstant.CUSTOMER_EMAIL);
        CraftingRingRequest request = CraftingRingRequest.builder()
                .customerId(1L)
                .branchId(1L)
                .self(self)
                .partner(partner)
                .build();

        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.CRAFTING_RING_PATH)
                .authorizationHeader(token)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenCreateSuccessfully(response);
    }

    private void thenCreateSuccessfully(WebTestClient.ResponseSpec response) {
        final NoResponse responseBody = response.expectBody(NoResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.INFO);
    }
}
