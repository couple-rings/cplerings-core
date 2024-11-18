package com.cplerings.core.application.transport.input;

import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;

import lombok.Builder;

@Builder
public record UpdateTransportationOrderStatusInput(Long transportationOrderId, ATransportationOrderStatus status) {
}
