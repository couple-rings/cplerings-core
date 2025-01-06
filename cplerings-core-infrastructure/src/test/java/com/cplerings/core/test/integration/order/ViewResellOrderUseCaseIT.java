package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.response.ViewResellOrderResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.ResellTestHelper;

public class ViewResellOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private ResellTestHelper resellTestHelper;

    @Test
    void givenStaff_whenViewSingleResellOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        resellTestHelper.createResellOrder();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.RESELL_ORDER_PATH, 1L)
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnResellOrder(response);
    }

    private void thenResponseReturnResellOrder(WebTestClient.ResponseSpec response) {
        final ViewResellOrderResponse responseBody = response.expectBody(ViewResellOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final AResellOrder resellOrder = responseBody.getData();
        assertThat(resellOrder).isNotNull();
        assertThat(resellOrder.getAmount()).isNotNull();
        assertThat(resellOrder.getCustomOrder().getFirstRing().getDiamonds()).isNotNull();
        assertThat(resellOrder.getCustomOrder().getFirstRing().getDiamonds()).isNotNull();
    }
}
