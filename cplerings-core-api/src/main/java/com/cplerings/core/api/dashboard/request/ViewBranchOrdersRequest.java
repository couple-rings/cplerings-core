package com.cplerings.core.api.dashboard.request;

import java.time.Instant;

import lombok.Builder;

@Builder
public record ViewBranchOrdersRequest(Instant startDate, Instant endDate) {
}
