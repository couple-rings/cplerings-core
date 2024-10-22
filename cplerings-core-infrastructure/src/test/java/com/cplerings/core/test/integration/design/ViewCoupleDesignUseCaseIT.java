package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.data.DesignCoupleInformation;
import com.cplerings.core.api.design.request.ViewDesignCouplesRequest;
import com.cplerings.core.api.design.response.ViewCoupleDesignResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;

class ViewCoupleDesignUseCaseIT extends AbstractIT {

    @Test
    void givenAnyone_whenViewCoupleDesign() {
        ViewDesignCouplesRequest request = new ViewDesignCouplesRequest.
                ViewDesignCouplesRequestBuilder()
                .minPrice(1D)
                .maxPrice(200000000000D)
                .collectionId(1L)
                .metalSpecificationId(11)
                .page(1)
                .pageSize(2)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DESIGN_COUPLE_PATH)
                .method(RequestBuilder.Method.GET)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnListOfDesignCouples(response);
    }

    private void thenResponseReturnListOfDesignCouples(WebTestClient.ResponseSpec response) {
        final ViewCoupleDesignResponse responseBody = response.expectBody(ViewCoupleDesignResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);
        assertThat(responseBody.getTotalPages())
                .isGreaterThan(0);
        assertThat(responseBody.getCount())
                .isGreaterThan(0);

        List<DesignCoupleInformation> data = responseBody.getData();
        assertThat(data.get(0).description())
                .isNotNull();
        assertThat(data.get(0).imageUrl())
                .isNotNull();
        assertThat(data.get(0).name())
                .isNotNull();
    }
}
