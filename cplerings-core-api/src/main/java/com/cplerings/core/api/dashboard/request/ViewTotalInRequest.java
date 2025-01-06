package com.cplerings.core.api.dashboard.request;

import java.time.Instant;

import lombok.Builder;

@Builder
public record ViewTotalInRequest(Instant startDate, Instant endDate) {
}
