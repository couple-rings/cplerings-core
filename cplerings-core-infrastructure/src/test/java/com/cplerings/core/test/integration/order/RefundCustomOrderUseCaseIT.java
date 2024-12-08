package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.request.data.RefundStandardOrderRequestData;
import com.cplerings.core.api.order.response.RefundCustomOrderResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.order.ARefund;
import com.cplerings.core.application.shared.entity.order.ARefundMethod;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.CustomOrderTestHelper;

class RefundCustomOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    @Test
    void givenStaff_whenRefundCustomOrder() {
        final String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        customOrderTestHelper.createCompleteCustomOrder();
        RefundStandardOrderRequestData refundStandardOrderRequestData = RefundStandardOrderRequestData.builder()
                .staffId(21L)
                .reason("test")
                .proofImageId(1L)
                .refundMethod(ARefundMethod.CASH)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.REFUND_CUSTOM_ORDER_PATH, 1L)
                .method(RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .body(refundStandardOrderRequestData)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnRefund(response);
    }

    private void thenResponseReturnRefund(WebTestClient.ResponseSpec response) {
        final RefundCustomOrderResponse responseBody = response.expectBody(RefundCustomOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final ARefund refundData = responseBody.getData();
        assertThat(refundData).isNotNull();
    }
}
