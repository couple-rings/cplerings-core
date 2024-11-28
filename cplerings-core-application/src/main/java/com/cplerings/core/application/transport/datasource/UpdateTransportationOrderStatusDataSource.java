package com.cplerings.core.application.transport.datasource;

import java.util.Optional;

import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportationOrder;

public interface UpdateTransportationOrderStatusDataSource {

    Optional<TransportationOrder> getTransportationOrderById(Long transportationId);

    TransportationOrder save(TransportationOrder transportationOrder);

    CustomOrder save(CustomOrder customOrder);

    CustomOrderHistory save(CustomOrderHistory customOrderHistory);

    TransportOrderHistory save(TransportOrderHistory transportOrderHistory);
}
