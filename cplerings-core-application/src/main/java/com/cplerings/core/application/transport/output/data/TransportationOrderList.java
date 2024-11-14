package com.cplerings.core.application.transport.output.data;

import java.util.List;

import com.cplerings.core.domain.order.TransportationOrder;

import lombok.Builder;

@Builder
public record TransportationOrderList(List<TransportationOrder> transportationOrders) {
}
