package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.request.CreateStandardOrderRequest;
import com.cplerings.core.api.order.response.CreateStandardOrderResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.entity.order.AStandardOrderStatus;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.jewelry.JewelryTestHelper;

class CreateStandardOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private JewelryTestHelper jewelryTestHelper;

    @Test
    void givenCustomer_whenCreateStandardOrderUseCase() {
        jewelryTestHelper.createJewelry();
        String token = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        List<Long> jewelryIds = new ArrayList<>();
        jewelryIds.add(1L);
        CreateStandardOrderRequest request = CreateStandardOrderRequest.builder()
                .jewelryIds(jewelryIds)
                .customerId(1L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.STANDARD_ORDER_PATH)
                .method(AbstractIT.RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnStandardOrder(response);
    }

    private void thenResponseReturnStandardOrder(WebTestClient.ResponseSpec response) {
        final CreateStandardOrderResponse responseBody = response.expectBody(CreateStandardOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final StandardOrderData standardOrder = responseBody.getData();
        assertThat(standardOrder).isNotNull();
        assertThat(standardOrder.standardOrder()).isNotNull();
        assertThat(standardOrder.standardOrder().getStatus()).isEqualByComparingTo(AStandardOrderStatus.PENDING);
        assertThat(standardOrder.standardOrder().getTotalPrice()).isNotEqualTo(0);
        assertThat(standardOrder.standardOrder().getJewelries()).isNotNull();
    }
}
