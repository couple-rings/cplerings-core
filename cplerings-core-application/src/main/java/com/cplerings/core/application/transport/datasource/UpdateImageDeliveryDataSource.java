package com.cplerings.core.application.transport.datasource;

import java.util.Optional;

import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.TransportationOrder;

public interface UpdateImageDeliveryDataSource {

    Optional<TransportationOrder> getTransportationOrder(Long transportationOrderId);

    Optional<Image> getImageById(Long imageId);

    TransportationOrder save(TransportationOrder transportationOrder);
}
