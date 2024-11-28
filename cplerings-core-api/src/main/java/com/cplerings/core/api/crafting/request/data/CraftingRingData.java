package com.cplerings.core.api.crafting.request.data;

import lombok.Builder;

@Builder
public record CraftingRingData(Long designId, Long spouseId, Long metalSpecId, Long diamondSpecId, String engraving, Integer fingerSize) {
}
