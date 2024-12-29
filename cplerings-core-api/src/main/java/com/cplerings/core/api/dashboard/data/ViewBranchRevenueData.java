package com.cplerings.core.api.dashboard.data;

import java.util.Map;

import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;

@Builder
public record ViewBranchRevenueData(Money totalRevenue, Map<String, Money> revenueForEach) {
}
