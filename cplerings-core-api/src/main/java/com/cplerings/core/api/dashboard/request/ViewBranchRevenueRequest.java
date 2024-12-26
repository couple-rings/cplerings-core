package com.cplerings.core.api.dashboard.request;

import java.time.Instant;

import lombok.Builder;

@Builder
public record ViewBranchRevenueRequest(Instant startDate, Instant endDate) {
}
