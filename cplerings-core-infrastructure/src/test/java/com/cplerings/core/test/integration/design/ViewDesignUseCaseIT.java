package com.cplerings.core.test.integration.design;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.design.response.ViewDesignResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;

class ViewDesignUseCaseIT extends AbstractIT {

    @Test
    void givenAnyone_whenViewDesignUseCase() {
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_DESIGN_PATH, 1)
                .method(AbstractIT.RequestBuilder.Method.GET)
                .send();

        thenResponseIsOk(response);
        thenReturnDesignData(response);
    }

    private void thenReturnDesignData(WebTestClient.ResponseSpec response) {
        final ViewDesignResponse responseBody = response.expectBody(ViewDesignResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);
        assertThat(responseBody.getData())
                .isNotNull()
                .isExactlyInstanceOf(ADesign.class);
        assertThat(responseBody.getData().getName()).isNotNull();
    }
}
