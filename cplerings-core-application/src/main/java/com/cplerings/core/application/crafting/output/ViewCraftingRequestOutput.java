package com.cplerings.core.application.crafting.output;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequest;

import lombok.Builder;

@Builder
public record ViewCraftingRequestOutput(ACraftingRequest craftingRequest) {
}
