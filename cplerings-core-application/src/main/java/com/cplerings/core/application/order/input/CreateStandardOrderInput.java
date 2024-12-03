package com.cplerings.core.application.order.input;

import java.util.List;

import com.cplerings.core.application.order.input.data.MetalSpecDesignIds;

import lombok.Builder;

@Builder
public record CreateStandardOrderInput(Long customerId, List<MetalSpecDesignIds> metalSpecDesignIds, Long branchId) {
}
