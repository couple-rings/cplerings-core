package com.cplerings.core.api.order.request;

import com.cplerings.core.application.shared.entity.order.OrderType;

import lombok.Builder;

@Builder
public record CompleteOrderRequest(Long orderId, OrderType orderType) {
}
