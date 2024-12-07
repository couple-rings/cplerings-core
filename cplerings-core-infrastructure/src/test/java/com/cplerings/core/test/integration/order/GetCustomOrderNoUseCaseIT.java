package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.order.response.GetCustomOrderByOrderNoResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.CustomOrderTestHelper;

public class GetCustomOrderNoUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    @Test
    void givenStaff_whenViewSingleCustomOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        CustomOrder customOrder = customOrderTestHelper.createCustomOrder();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_A_CUSTOM_ORDER_BY_ORDER_NO_PATH, customOrder.getOrderNo())
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomOrder(response);
    }

    private void thenResponseReturnCustomOrder(WebTestClient.ResponseSpec response) {
        final GetCustomOrderByOrderNoResponse responseBody = response.expectBody(GetCustomOrderByOrderNoResponse.class)
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
