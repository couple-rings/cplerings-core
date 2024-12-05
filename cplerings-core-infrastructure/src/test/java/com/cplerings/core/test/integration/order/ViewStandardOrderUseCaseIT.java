package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.response.ViewStandardOrderResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractLoggingIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.StandardOrderTestHelper;

public class ViewStandardOrderUseCaseIT extends AbstractLoggingIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private StandardOrderTestHelper standardOrderTestHelper;

    @Test
    void givenStaff_whenViewSingleCustomOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        standardOrderTestHelper.createStandardOrder();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.SINGLE_STANDARD_ORDER_PATH, 1L)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomOrder(response);
    }

    private void thenResponseReturnCustomOrder(WebTestClient.ResponseSpec response) {
        final ViewStandardOrderResponse responseBody = response.expectBody(ViewStandardOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final StandardOrderData standardOrderData = responseBody.getData();
        assertThat(standardOrderData).isNotNull();
        assertThat(standardOrderData.standardOrder().getStandardOrderItems()).allSatisfy(x -> {
            assertThat(x.getBranch()).isNotNull();
            assertThat(x.getDesign()).isNotNull();
            assertThat(x.getMetalSpecification()).isNotNull();
            assertThat(x.getMetalSpecification().getName()).isNotNull();
            assertThat(x.getJewelry().getBranch()).isNotNull();
        });
    }
}
