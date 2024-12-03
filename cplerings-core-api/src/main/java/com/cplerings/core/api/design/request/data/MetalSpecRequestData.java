package com.cplerings.core.api.design.request.data;

import lombok.Builder;

@Builder
public record MetalSpecRequestData(Long metalSpecId, Long imageId) {
}
