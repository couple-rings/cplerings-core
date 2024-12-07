package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.request.CompleteOrderRequest;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.application.shared.entity.order.OrderType;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.StandardOrderTestHelper;

class CompleteOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private StandardOrderTestHelper standardOrderTestHelper;

    @Test
    void givenStaff_whenCompleteStandardOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        standardOrderTestHelper.createPaidStandardOrder();
        CompleteOrderRequest request = CompleteOrderRequest.builder()
                .orderId(1L)
                .orderType(OrderType.STANDARD)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.COMPLETE_ORDER_PATH)
                .method(RequestBuilder.Method.PUT)
                .authorizationHeader(token)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenReturnNoResponse(response);
    }

    private void thenReturnNoResponse(WebTestClient.ResponseSpec response) {
        final NoResponse responseBody = response.expectBody(NoResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.INFO);
    }
}
