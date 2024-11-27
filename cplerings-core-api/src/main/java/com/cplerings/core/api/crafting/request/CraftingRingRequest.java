package com.cplerings.core.api.crafting.request;

import com.cplerings.core.api.crafting.request.data.CraftingRingData;

import lombok.Builder;

@Builder
public record CraftingRingRequest(Long customerId, Long branchId, CraftingRingData self, CraftingRingData partner) {
}
