package com.cplerings.core.application.dashboard.datasource.data;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public record Revenue(BigDecimal totalRevenue, List<BigDecimal> revenueForEach) {
}
