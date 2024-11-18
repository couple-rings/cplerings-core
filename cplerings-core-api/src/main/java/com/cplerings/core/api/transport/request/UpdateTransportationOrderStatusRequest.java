package com.cplerings.core.api.transport.request;

import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;

import lombok.Builder;

@Builder
public record UpdateTransportationOrderStatusRequest(Long transportationOrderId, ATransportationOrderStatus status) {
}
