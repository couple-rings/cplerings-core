package com.cplerings.core.test.integration.jewelry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.jewelry.data.JewelryCategoriesData;
import com.cplerings.core.api.jewelry.request.ViewJewelryCategoriesRequest;
import com.cplerings.core.api.jewelry.response.ViewJewelryCategoriesResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;

class ViewJewelryCategoriesUseCaseIT extends AbstractIT {

    @Test
    void givenAnyone_whenViewJewelryCategories() {
        ViewJewelryCategoriesRequest request = ViewJewelryCategoriesRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.JEWELRIES_CATEGORIES_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnJewelryCategories(response);
    }

    private void thenResponseReturnJewelryCategories(WebTestClient.ResponseSpec response) {
        final ViewJewelryCategoriesResponse responseBody = response.expectBody(ViewJewelryCategoriesResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final JewelryCategoriesData jewelryCategoriesData = responseBody.getData();
        assertThat(jewelryCategoriesData).isNotNull();
        assertThat(jewelryCategoriesData.getPage()).isZero();
        assertThat(jewelryCategoriesData.getPageSize()).isEqualTo(1);
        assertThat(jewelryCategoriesData.getItems()).hasSize(1);
    }
}
