package com.cplerings.core.application.transport.datasource;

import java.util.List;

import com.cplerings.core.domain.order.TransportationOrder;

public interface UpdateTransportationOrdersToOngoingDataSource {

    List<TransportationOrder> getTransportationOrders(List<Long> ids);
    List<TransportationOrder> updateToOngoing(List<TransportationOrder> orders);
}
