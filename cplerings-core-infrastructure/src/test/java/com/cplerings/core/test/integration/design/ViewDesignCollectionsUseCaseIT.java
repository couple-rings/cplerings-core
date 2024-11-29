package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.data.DesignCollectionsData;
import com.cplerings.core.api.design.request.ViewDesignCollectionsRequest;
import com.cplerings.core.api.design.response.ViewDesignCollectionsResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;

public class ViewDesignCollectionsUseCaseIT extends AbstractIT {

    @Test
    void givenAnyone_whenViewDesignCollections() {
        ViewDesignCollectionsRequest request = ViewDesignCollectionsRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_DESIGN_COLLECTION_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnDesignCollections(response);
    }

    private void thenResponseReturnDesignCollections(WebTestClient.ResponseSpec response) {
        final ViewDesignCollectionsResponse responseBody = response.expectBody(ViewDesignCollectionsResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final DesignCollectionsData designCollectionsData = responseBody.getData();
        assertThat(designCollectionsData).isNotNull();
        assertThat(designCollectionsData.getPage()).isZero();
        assertThat(designCollectionsData.getPageSize()).isEqualTo(1);
        assertThat(designCollectionsData.getItems()).hasSize(1);
        assertThat(designCollectionsData.getItems()).hasSize(1)
                .allSatisfy(item -> assertThat(item).isNotNull());
    }
}
