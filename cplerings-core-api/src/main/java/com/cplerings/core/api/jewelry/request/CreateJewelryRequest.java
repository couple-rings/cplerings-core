package com.cplerings.core.api.jewelry.request;

import lombok.Builder;

@Builder
public record CreateJewelryRequest(Long metalSpecId, Long designId, Long branchId) {
}
