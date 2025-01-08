package com.cplerings.core.api.dashboard.request;

import java.time.Instant;

import com.cplerings.core.application.shared.entity.order.OrderType;

import lombok.Builder;

@Builder
public record ViewTotalPaymentPerOrderRequest(Instant startDate, Instant endDate, OrderType orderType) {
}
