package com.cplerings.core.application.dashboard.input;

import java.time.Instant;

import lombok.Builder;

@Builder
public record ViewTotalExpenditureInput(Instant startDate, Instant endDate) {
}
