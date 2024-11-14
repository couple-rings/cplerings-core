package com.cplerings.core.api.transport.data;

import java.util.List;

import com.cplerings.core.application.shared.entity.order.ATransportationOrder;

import lombok.Builder;

@Builder
public record TransportationOrderList(List<ATransportationOrder> transportationOrders) {
}
