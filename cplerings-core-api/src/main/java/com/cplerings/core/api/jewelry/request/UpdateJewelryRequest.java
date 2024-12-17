package com.cplerings.core.api.jewelry.request;

import lombok.Builder;

@Builder
public record UpdateJewelryRequest(Long jewelryId, Long metalSpecId, Long designId) {
}
