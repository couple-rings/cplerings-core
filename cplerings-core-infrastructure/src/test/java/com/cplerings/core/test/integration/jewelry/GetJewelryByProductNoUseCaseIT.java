package com.cplerings.core.test.integration.jewelry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.jewelry.response.GetJewelryByProductNoResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.jewelry.JewelryTestHelper;

public class GetJewelryByProductNoUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private JewelryTestHelper jewelryTestHelper;

    private Jewelry jewelry;

    @Test
    void givenStaff_whenViewSingleCustomOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        this.jewelry = jewelryTestHelper.createJewelry();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.JEWELRY_PRODUCT_NO_PATH, this.jewelry.getProductNo())
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnJewelry(response);
    }

    private void thenResponseReturnJewelry(WebTestClient.ResponseSpec response) {
        final GetJewelryByProductNoResponse responseBody = response.expectBody(GetJewelryByProductNoResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final AJewelry aJewelry = responseBody.getData();
        assertThat(aJewelry).isNotNull();
    }
}
