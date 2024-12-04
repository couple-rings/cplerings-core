package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.data.DesignsData;
import com.cplerings.core.api.design.request.ViewDesignsRequest;
import com.cplerings.core.api.design.response.ViewDesignsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;

class ViewDesignsUseCaseIT extends AbstractIT {

    @Test
    void givenAnyone_whenViewDesigns() {
        ViewDesignsRequest request = ViewDesignsRequest.builder()
                .page(0)
                .pageSize(1)
                .categoryId(1L)
                .designCollectionId(1L)
                .metalSpecId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DESIGN_PATH)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnDesigns(response);

    }

    private void thenResponseReturnDesigns(WebTestClient.ResponseSpec response) {
        final ViewDesignsResponse responseBody = response.expectBody(ViewDesignsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final DesignsData designs = responseBody.getData();
        assertThat(designs).isNotNull();
        assertThat(designs.getPage()).isZero();
        assertThat(designs.getPageSize()).isEqualTo(1);
    }
}
