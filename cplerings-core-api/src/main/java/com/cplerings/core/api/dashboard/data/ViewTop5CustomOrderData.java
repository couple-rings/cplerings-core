package com.cplerings.core.api.dashboard.data;

import java.util.List;

import com.cplerings.core.application.dashboard.output.ViewTop5CustomOrderOutput;

import lombok.Builder;

@Builder
public record ViewTop5CustomOrderData(List<ViewTop5CustomOrderOutput> top5CustomOrder) {
}
