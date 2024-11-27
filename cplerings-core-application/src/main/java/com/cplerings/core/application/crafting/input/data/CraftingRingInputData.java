package com.cplerings.core.application.crafting.input.data;

import lombok.Builder;

@Builder
public record CraftingRingInputData(Long designId, Long spouseId, Long metalSpecId, Long diamondSpecId, String engraving, Integer fingerSize) {
}
