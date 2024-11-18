package com.cplerings.core.api.transport.request.data;

import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;

import lombok.Builder;

@Builder
public record UpdateTransportationOrderStatusRequestData(ATransportationOrderStatus status) {
}
