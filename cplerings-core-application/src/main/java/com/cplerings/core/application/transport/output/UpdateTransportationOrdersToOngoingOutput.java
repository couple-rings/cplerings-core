package com.cplerings.core.application.transport.output;

import java.util.List;

import com.cplerings.core.application.shared.entity.order.ATransportationOrder;

import lombok.Builder;

@Builder
public record UpdateTransportationOrdersToOngoingOutput(List<ATransportationOrder> transportationOrders) {
}
