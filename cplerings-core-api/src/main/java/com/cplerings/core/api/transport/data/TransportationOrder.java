package com.cplerings.core.api.transport.data;

import com.cplerings.core.application.shared.entity.order.ATransportationOrder;

import lombok.Builder;

@Builder
public record TransportationOrder(ATransportationOrder transportationOrder) {
}
