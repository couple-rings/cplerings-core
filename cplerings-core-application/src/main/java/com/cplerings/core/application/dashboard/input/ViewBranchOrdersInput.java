package com.cplerings.core.application.dashboard.input;

import java.time.Instant;

import lombok.Builder;

@Builder
public record ViewBranchOrdersInput(Instant startDate, Instant endDate) {
}
