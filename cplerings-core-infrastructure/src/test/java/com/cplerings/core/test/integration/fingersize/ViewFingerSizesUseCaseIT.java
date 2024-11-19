package com.cplerings.core.test.integration.fingersize;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.fingersize.data.FingerSizesData;
import com.cplerings.core.api.fingersize.request.ViewFingerSizesRequest;
import com.cplerings.core.api.fingersize.response.ViewFingerSizesResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;

class ViewFingerSizesUseCaseIT extends AbstractIT {

    @Test
    void givenAnyone_whenViewFingerSizes() {
        ViewFingerSizesRequest request = ViewFingerSizesRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.FINGER_SIZES_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnBranches(response);
    }

    private void thenResponseReturnBranches(WebTestClient.ResponseSpec response) {
        final ViewFingerSizesResponse responseBody = response.expectBody(ViewFingerSizesResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final FingerSizesData fingerSizesData = responseBody.getData();
        assertThat(fingerSizesData).isNotNull();
        assertThat(fingerSizesData.getPage()).isZero();
        assertThat(fingerSizesData.getPageSize()).isEqualTo(1);
        assertThat(fingerSizesData.getItems()).hasSize(1);
    }
}
