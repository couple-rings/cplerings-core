package com.cplerings.core.api.order.request;

import lombok.Builder;

@Builder
public record CancelCustomOrderRequest(Long customOrderId) {

}
