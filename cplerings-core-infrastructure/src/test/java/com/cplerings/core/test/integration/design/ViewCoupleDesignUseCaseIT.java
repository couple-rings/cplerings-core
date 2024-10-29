package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.design.data.DesignCoupleData;
import com.cplerings.core.api.design.request.ViewDesignCouplesRequest;
import com.cplerings.core.api.design.response.ViewCoupleDesignResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

class ViewCoupleDesignUseCaseIT extends AbstractIT {

    @Test
    void givenAnyone_whenViewCoupleDesign() {
        ViewDesignCouplesRequest request = ViewDesignCouplesRequest.builder()
                .minPrice(BigDecimal.valueOf(1))
                .maxPrice(BigDecimal.valueOf(150000000L))
                .collectionId(1L)
                .metalSpecificationId(1L)
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

        final DesignCoupleData designCoupleData = responseBody.getData();
        assertThat(designCoupleData).isNotNull();
        assertThat(designCoupleData.getPage()).isEqualTo(0);
        assertThat(designCoupleData.getPageSize()).isEqualTo(10);
        assertThat(designCoupleData.getItems()).hasSize(1);
        assertThat(designCoupleData.getTotalPages()).isEqualTo(1);
    }
}
