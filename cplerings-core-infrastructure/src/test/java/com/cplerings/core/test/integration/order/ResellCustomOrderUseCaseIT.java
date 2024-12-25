package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.request.data.ResellCustomOrderRequestData;
import com.cplerings.core.api.order.response.ResellCustomOrderResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.CustomOrderTestHelper;

public class ResellCustomOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    @Test
    void givenStaff_whenRefundCustomOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        customOrderTestHelper.createCompleteCustomOrder();
        ResellCustomOrderRequestData request = ResellCustomOrderRequestData.builder()
                .customerId(1L)
                .note("test")
                .proofImageId(1L)
                .paymentMethod(APaymentMethod.CASH)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.RESELL_CUSTOM_ORDER_PATH, 1L)
                .method(RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnRefund(response);
    }

    private void thenResponseReturnRefund(WebTestClient.ResponseSpec response) {
        final ResellCustomOrderResponse responseBody = response.expectBody(ResellCustomOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final AResellOrder resellOrder = responseBody.getData();
        assertThat(resellOrder).isNotNull();
        assertThat(resellOrder.getNote()).isNotNull();
    }
}
