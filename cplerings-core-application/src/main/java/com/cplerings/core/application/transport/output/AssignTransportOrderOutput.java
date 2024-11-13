package com.cplerings.core.application.transport.output;

import com.cplerings.core.application.shared.entity.order.ATransportationOrder;

import lombok.Builder;

@Builder
public record AssignTransportOrderOutput(ATransportationOrder transportationOrder) {
}
