package com.cplerings.core.api.order.request;

import lombok.Builder;

@Builder
public record CancelStandardOrderRequest(Long standardOrderId) {
}