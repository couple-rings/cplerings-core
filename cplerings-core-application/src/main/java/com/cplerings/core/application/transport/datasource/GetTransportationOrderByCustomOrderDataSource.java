package com.cplerings.core.application.transport.datasource;

import java.util.Optional;

import com.cplerings.core.domain.order.TransportationOrder;

public interface GetTransportationOrderByCustomOrderDataSource {

    Optional<TransportationOrder> getTransportationOrderByCustomOrderId(Long customOrderId);
}
