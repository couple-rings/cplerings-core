package com.cplerings.core.api.order.request;

import lombok.Builder;

@Builder
public record PayStandardOrderRequest(Long standardOrderId) {

}
