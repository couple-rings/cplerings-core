package com.cplerings.core.api.order.request;

import lombok.Builder;

@Builder
public record ViewCustomOrderRequest(Long customOrderId) {
}
