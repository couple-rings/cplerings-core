package com.cplerings.core.application.order.input;

import com.cplerings.core.application.shared.entity.order.OrderType;

import lombok.Builder;

@Builder
public record CompleteOrderInput(Long orderId, OrderType orderType) {
}
