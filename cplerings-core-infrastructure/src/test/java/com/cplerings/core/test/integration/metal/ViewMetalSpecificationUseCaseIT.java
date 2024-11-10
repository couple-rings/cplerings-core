package com.cplerings.core.test.integration.metal;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.metal.data.MetalSpecification;
import com.cplerings.core.api.metal.request.ViewMetalSpecificationRequest;
import com.cplerings.core.api.metal.response.ViewMetalSpecificationResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

public class ViewMetalSpecificationUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenAnyone_whenViewMetalSpecifications() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        ViewMetalSpecificationRequest request = ViewMetalSpecificationRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.METAL_SPECIFICATION_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnMetalSpecifications(response);
    }

    private void thenResponseReturnMetalSpecifications(WebTestClient.ResponseSpec response) {
        final ViewMetalSpecificationResponse responseBody = response.expectBody(ViewMetalSpecificationResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final MetalSpecification metalSpecification = responseBody.getData();
        assertThat(metalSpecification).isNotNull();
        assertThat(metalSpecification.getPage()).isZero();
        assertThat(metalSpecification.getPageSize()).isEqualTo(1);
        assertThat(metalSpecification.getItems()).hasSize(1);
        assertThat(metalSpecification.getTotalPages()).isEqualTo(6);
    }
}
