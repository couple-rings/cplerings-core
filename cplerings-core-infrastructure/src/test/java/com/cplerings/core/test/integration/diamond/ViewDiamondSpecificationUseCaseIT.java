package com.cplerings.core.test.integration.diamond;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.api.diamond.data.DiamondSpecification;
import com.cplerings.core.api.diamond.request.ViewDiamondSpecificationRequest;
import com.cplerings.core.api.diamond.response.ViewDiamondSpecificationResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class ViewDiamondSpecificationUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Test
    void givenAnyone_whenViewDiamondSpecifications() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        ViewDiamondSpecificationRequest request = ViewDiamondSpecificationRequest.builder()
                .page(0)
                .pageSize(1)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.DIAMOND_SPECIFICATION_PATH)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .query(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnDiamondSpecifications(response);
    }

    private void thenResponseReturnDiamondSpecifications(WebTestClient.ResponseSpec response) {
        final ViewDiamondSpecificationResponse responseBody = response.expectBody(ViewDiamondSpecificationResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.PAGINATED_DATA);

        final DiamondSpecification diamondSpecification = responseBody.getData();
        assertThat(diamondSpecification).isNotNull();
        assertThat(diamondSpecification.getPage()).isZero();
        assertThat(diamondSpecification.getPageSize()).isEqualTo(1);
        assertThat(diamondSpecification.getItems()).hasSize(1);
        assertThat(diamondSpecification.getTotalPages()).isEqualTo(9);
    }
}
