package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.response.GetStandardOrderByOrderNoResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.StandardOrderTestHelper;

public class GetStandardOrderByOrderNoUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private StandardOrderTestHelper standardOrderTestHelper;

    @Test
    void givenStaff_whenViewSingleCustomOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        StandardOrder standardOrder = standardOrderTestHelper.createCompleteStandardOrder();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.VIEW_A_STANDARD_ORDER_BY_ORDER_NO_PATH, standardOrder.getOrderNo())
                .method(RequestBuilder.Method.GET)
                .authorizationHeader(token)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomOrder(response);
    }

    private void thenResponseReturnCustomOrder(WebTestClient.ResponseSpec response) {
        final GetStandardOrderByOrderNoResponse responseBody = response.expectBody(GetStandardOrderByOrderNoResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final StandardOrderData customOrdersData = responseBody.getData();
        assertThat(customOrdersData).isNotNull();
    }
}
