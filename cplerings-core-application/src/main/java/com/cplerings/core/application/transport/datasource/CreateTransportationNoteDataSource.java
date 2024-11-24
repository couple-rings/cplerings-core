package com.cplerings.core.application.transport.datasource;

import java.util.Optional;

import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.order.status.TransportationNote;

public interface CreateTransportationNoteDataSource {

    Optional<TransportationOrder> getTransportationOrder(Long transportationNoteId);

    TransportationNote save(TransportationNote transportationNote);
}
