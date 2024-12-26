package com.cplerings.core.api.dashboard.data;

import java.util.List;

import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;

@Builder
public record ViewBranchRevenueData(Money totalRevenue, List<Money> revenueForEach) {
}
