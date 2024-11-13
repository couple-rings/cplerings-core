package com.cplerings.core.test.shared.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public class TransportOrderTestHelper {

    @Autowired
    private CustomOrderTestHelper customOrderTestHelper;

    @Autowired
    private TestDataSource testDataSource;

    public TransportationOrder createTransportOrder() {
        TransportationOrder transportationOrder = TransportationOrder.builder()
                .customOrder(customOrderTestHelper.createCustomOrder())
                .deliveryAddress("Test")
                .receiverName("Test")
                .receiverPhone("Test")
                .status(TransportStatus.PENDING)
                .build();
        return testDataSource.save(transportationOrder);
    }
}