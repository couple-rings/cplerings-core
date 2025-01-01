package com.cplerings.core.application.dashboard.datasource.data;

import java.time.Instant;

import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;

@Builder
public record CombinedOrder(Long orderId, Money amount, OrderTypeStatistic orderType, APaymentMethod paymentMethod, String orderNo, Instant createdAt) {
}
