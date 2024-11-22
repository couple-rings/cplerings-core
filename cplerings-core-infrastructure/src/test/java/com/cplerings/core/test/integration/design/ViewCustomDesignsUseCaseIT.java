package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.crafting.request.ViewCraftingRequestsRequest;
import com.cplerings.core.api.design.data.CustomDesigns;
import com.cplerings.core.api.design.request.ViewCustomDesignsRequest;
import com.cplerings.core.api.design.response.ViewCustomDesignsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.shared.AState;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.design.CustomDesignTestHelper;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

class ViewCustomDesignsUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CustomDesignTestHelper customDesignTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenAnyone_whenViewCustomDesigns() {
        CustomDesign customDesign = customDesignTestHelper.createCustomDesignInactive();
        testDataSource.save(customDesign);
        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        ViewCustomDesignsRequest request = ViewCustomDesignsRequest.builder()
                .page(0)
                .pageSize(1)
                .state(AState.INACTIVE)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_CUSTOM_DESIGNS_PATH)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomDesigns(response);

    }

    private void thenResponseReturnCustomDesigns(WebTestClient.ResponseSpec response) {
        final ViewCustomDesignsResponse responseBody = response.expectBody(ViewCustomDesignsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final CustomDesigns customDesigns = responseBody.getData();
        assertThat(customDesigns).isNotNull();
        assertThat(customDesigns.getPage()).isZero();
        assertThat(customDesigns.getPageSize()).isEqualTo(1);
        assertThat(customDesigns.getItems()).hasSize(1);
        assertThat(customDesigns.getTotalPages()).isEqualTo(1);
    }
}
