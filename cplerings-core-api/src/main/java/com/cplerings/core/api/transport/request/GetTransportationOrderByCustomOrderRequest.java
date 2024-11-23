package com.cplerings.core.api.transport.request;

import lombok.Builder;

@Builder
public record GetTransportationOrderByCustomOrderRequest(Long customOrderId) {
}
