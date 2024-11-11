package com.cplerings.core.test.integration.crafting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.crafting.request.ViewCraftingRequestsRequest;
import com.cplerings.core.api.design.data.CustomRequestsData;
import com.cplerings.core.api.design.response.ViewCustomRequestsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

public class ViewCraftingRequestsUseCaseIT extends AbstractIT{

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenAnyone_whenViewCustomRequests() {
        CraftingRequest craftingRequest = CraftingRequest.builder()
                .customDesign()
                .build();

        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        ViewCraftingRequestsRequest request = ViewCraftingRequestsRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_CUSTOM_REQUESTS_PATH)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomRequests(response);

    }

    private void thenResponseReturnCustomRequests(WebTestClient.ResponseSpec response) {
        final ViewCustomRequestsResponse responseBody = response.expectBody(ViewCustomRequestsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final CustomRequestsData designCoupleData = responseBody.getData();
        assertThat(designCoupleData).isNotNull();
        assertThat(designCoupleData.getPage()).isZero();
        assertThat(designCoupleData.getPageSize()).isEqualTo(1);
        assertThat(designCoupleData.getItems()).hasSize(1);
        assertThat(designCoupleData.getTotalPages()).isEqualTo(1);
    }
}
