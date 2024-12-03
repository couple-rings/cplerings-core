package com.cplerings.core.api.order.request;

import java.util.List;

import com.cplerings.core.api.order.request.data.MetalSpecDesignIds;

import lombok.Builder;

@Builder
public record CreateStandardOrderRequest(Long customerId, List<MetalSpecDesignIds> metalSpecDesignIds, Long branchId) {
}
