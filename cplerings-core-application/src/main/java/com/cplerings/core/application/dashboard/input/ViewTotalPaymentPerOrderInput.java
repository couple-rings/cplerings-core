package com.cplerings.core.application.dashboard.input;

import java.time.Instant;

import com.cplerings.core.application.shared.entity.order.OrderType;

import lombok.Builder;

@Builder
public record ViewTotalPaymentPerOrderInput(Instant startDate, Instant endDate, OrderType orderType) {
}
