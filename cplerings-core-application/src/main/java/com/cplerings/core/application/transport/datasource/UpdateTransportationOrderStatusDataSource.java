package com.cplerings.core.application.transport.datasource;

import java.util.Optional;

import com.cplerings.core.domain.order.TransportationOrder;

public interface UpdateTransportationOrderStatusDataSource {

    Optional<TransportationOrder> getTransportationOrderById(Long transportationId);

    TransportationOrder save(TransportationOrder transportationOrder);
}
