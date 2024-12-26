package com.cplerings.core.application.dashboard.output;

import java.util.List;

import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;

@Builder
public record ViewBranchRevenueOutput(Money totalRevenue, List<Money> revenueForEach) {
}
