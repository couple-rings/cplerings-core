package com.cplerings.core.application.dashboard.output;

import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;

@Builder
public record ViewTotalRevenueOutput(Money totalRevenue) {
}
