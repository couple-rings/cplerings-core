package com.cplerings.core.application.dashboard.datasource.data;

import java.util.List;
import java.util.Map;

import lombok.Builder;

@Builder
public record Orders(Long totalCustomOrder, Long totalResellOrder, Long totalRefundOrder, Map<String, Long> customOrdersForEach, Map<String, Long> resellOrdersForEach, Map<String, Long> refundOrdersForEach) {
}
