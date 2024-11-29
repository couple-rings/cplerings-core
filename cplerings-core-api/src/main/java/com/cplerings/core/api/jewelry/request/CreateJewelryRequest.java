package com.cplerings.core.api.jewelry.request;

import lombok.Builder;

@Builder
public record CreateJewelryRequest(Long diamondId, Long metalSpecId, Long designId, Long branchId) {
}
