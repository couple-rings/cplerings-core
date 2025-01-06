package com.cplerings.core.application.dashboard.output;

import java.util.List;

import lombok.Builder;

@Builder
public record ViewTop5CustomOrderOutputData(List<ViewTop5CustomOrderOutput> top5CustomOrder) {
}
