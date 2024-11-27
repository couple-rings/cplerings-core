package com.cplerings.core.application.crafting.input;

import com.cplerings.core.application.crafting.input.data.CraftingRingInputData;

import lombok.Builder;

@Builder
public record CraftingRingInput(Long customerId, Long branchId, CraftingRingInputData self,
                                CraftingRingInputData partner) {
}
