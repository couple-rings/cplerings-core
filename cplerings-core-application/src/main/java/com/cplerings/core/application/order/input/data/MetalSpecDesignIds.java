package com.cplerings.core.application.order.input.data;

import lombok.Builder;

@Builder
public record MetalSpecDesignIds(Long metalSpecId, Long designId) {
}
