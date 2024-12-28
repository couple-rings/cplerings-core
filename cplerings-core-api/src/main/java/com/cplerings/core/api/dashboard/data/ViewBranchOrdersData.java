package com.cplerings.core.api.dashboard.data;

import com.cplerings.core.application.dashboard.datasource.data.Orders;

import lombok.Builder;

@Builder
public record ViewBranchOrdersData(Orders orders) {
}
