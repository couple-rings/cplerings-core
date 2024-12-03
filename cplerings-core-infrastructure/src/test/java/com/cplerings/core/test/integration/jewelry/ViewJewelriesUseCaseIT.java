package com.cplerings.core.test.integration.jewelry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.jewelry.data.JewelriesData;
import com.cplerings.core.api.jewelry.request.ViewJewelriesRequest;
import com.cplerings.core.api.jewelry.response.ViewJewelriesResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.jewelry.JewelryTestHelper;

class ViewJewelriesUseCaseIT extends AbstractIT {

    @Autowired
    private JewelryTestHelper jewelryTestHelper;

    @Test
    void givenAnyone_whenViewJewelries() {
        jewelryTestHelper.createJewelry();
        ViewJewelriesRequest request = ViewJewelriesRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.JEWELRIES_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnJewelries(response);
    }

    private void thenResponseReturnJewelries(WebTestClient.ResponseSpec response) {
        final ViewJewelriesResponse responseBody = response.expectBody(ViewJewelriesResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final JewelriesData jewelriesData = responseBody.getData();
        assertThat(jewelriesData).isNotNull();
        assertThat(jewelriesData.getPage()).isZero();
        assertThat(jewelriesData.getPageSize()).isEqualTo(1);
        assertThat(jewelriesData.getItems()).hasSize(1);
    }
}
