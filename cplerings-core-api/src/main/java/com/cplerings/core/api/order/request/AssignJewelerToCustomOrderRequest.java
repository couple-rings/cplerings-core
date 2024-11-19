package com.cplerings.core.api.order.request;

import lombok.Builder;

@Builder
public record AssignJewelerToCustomOrderRequest(Long customOrderId, Long jewelerId) {
}
