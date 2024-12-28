package com.cplerings.core.application.dashboard.output;

import com.cplerings.core.application.dashboard.datasource.data.Orders;

import lombok.Builder;

@Builder
public record ViewBranchOrdersOutput(Orders orders) {
}
