package com.cplerings.core.application.dashboard.datasource.data;

import java.util.List;

import lombok.Builder;

@Builder
public record Orders(Long totalCustomOrder, Long totalResellOrder, Long totalRefundOrder, List<Long> customOrdersForEach, List<Long> resellOrdersForEach, List<Long> refundOrdersForEach) {
}
