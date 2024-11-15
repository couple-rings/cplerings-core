package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.order.response.ViewCustomOrderResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.CustomOrderTestHelper;

class ViewCustomOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    @Test
    void givenStaff_whenViewSingleCustomOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        customOrderTestHelper.createCustomOrder();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_A_CUSTOM_ORDER_PATH.replace("{customOrderId}", Long.toString(1L)))
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomOrder(response);
    }

    private void thenResponseReturnCustomOrder(WebTestClient.ResponseSpec response) {
        final ViewCustomOrderResponse responseBody = response.expectBody(ViewCustomOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final CustomOrderData customOrdersData = responseBody.getData();
        assertThat(customOrdersData).isNotNull();
    }
}
