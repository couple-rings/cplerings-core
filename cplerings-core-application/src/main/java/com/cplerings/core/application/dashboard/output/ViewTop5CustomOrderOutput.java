package com.cplerings.core.application.dashboard.output;

import java.time.Instant;

import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.entity.order.OrderType;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;

@Builder
public record ViewTop5CustomOrderOutput(Money totalPrice, Instant createdAt, OrderType orderType, APaymentMethod paymentMethod, String orderNo) {
}
