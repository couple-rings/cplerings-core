package com.cplerings.core.test.integration.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.order.request.data.AssignJewelerToCustomOrderRequestData;
import com.cplerings.core.api.order.response.AssignJewelerToCustomOrderResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.datasource.TestDataSource;
import com.cplerings.core.test.shared.helper.JWTTestHelper;
import com.cplerings.core.test.shared.order.CustomOrderTestHelper;

class AssignJewelerToCustomOrderUseCaseIT extends AbstractIT {

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    @Test
    void givenStaff_whenAssignTransportOrderUseCase() {
        CustomOrder customOrder = customOrderTestHelper.createCustomOrder();
        customOrder.setStatus(CustomOrderStatus.WAITING);
        customOrder.setJeweler(null);
        testDataSource.save(customOrder);
        String token = jwtTestHelper.generateToken(AccountTestConstant.STAFF_EMAIL);
        AssignJewelerToCustomOrderRequestData request = AssignJewelerToCustomOrderRequestData.builder()
                .jewelerId(41L)
                .build();
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.ASSIGN_CUSTOM_ORDER_TO_JEWELER_PATH.replace("{customOrderId}", Long.toString(1L)))
                .method(AbstractIT.RequestBuilder.Method.POST)
                .authorizationHeader(token)
                .body(request)
                .send();
        thenResponseIsOk(response);
        thenResponseReturnCustomOrder(response);
    }

    private void thenResponseReturnCustomOrder(WebTestClient.ResponseSpec response) {
        final AssignJewelerToCustomOrderResponse responseBody = response.expectBody(AssignJewelerToCustomOrderResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isNotNull();
        assertThat(responseBody.getType())
                .isEqualTo(AbstractResponse.Type.DATA);

        final CustomOrderData customOrderData = responseBody.getData();
        assertThat(customOrderData).isNotNull();
        assertThat(customOrderData.customOrder().getJeweler()).isNotNull();
        assertThat(customOrderData.customOrder().getStatus()).isEqualByComparingTo(CustomOrderStatus.IN_PROGRESS);
    }
}
