package com.cplerings.core.api.order.request;

import lombok.Builder;

@Builder
public record GetCustomOrderByOrderNoRequest(String orderNo) {
}
