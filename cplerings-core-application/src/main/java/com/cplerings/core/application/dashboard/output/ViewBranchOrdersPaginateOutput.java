package com.cplerings.core.application.dashboard.output;

import com.cplerings.core.application.dashboard.datasource.data.CombinedOrders;

import lombok.Builder;

@Builder
public record ViewBranchOrdersPaginateOutput(CombinedOrders orders) {
}
