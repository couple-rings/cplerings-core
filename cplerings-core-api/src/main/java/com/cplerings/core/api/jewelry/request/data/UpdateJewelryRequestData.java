package com.cplerings.core.api.jewelry.request.data;

import lombok.Builder;

@Builder
public record UpdateJewelryRequestData(Long metalSpecId, Long designId) {
}
