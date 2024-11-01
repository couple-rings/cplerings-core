package com.cplerings.core.test.integration.craftingrequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BIG_DECIMAL;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.craftingrequest.data.CraftingRequest;
import com.cplerings.core.api.craftingrequest.request.CreateCraftingRequestRequest;
import com.cplerings.core.api.craftingrequest.response.CreateCraftingRequestResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.shared.valueobject.Weight;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class CreateCraftingRequestIT extends AbstractIT{

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenCustomer_whenCreateCraftingRequestUseCase() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);

//        CustomDesign customDesign = CustomDesign.builder()
//                .metalWeight(new Weight(BigDecimal.valueOf(1)))
//                .build();

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
