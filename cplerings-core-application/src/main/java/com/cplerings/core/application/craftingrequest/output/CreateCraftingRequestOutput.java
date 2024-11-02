package com.cplerings.core.application.craftingrequest.output;

import com.cplerings.core.application.shared.entity.craftingrequest.ACraftingRequest;

import lombok.Builder;

@Builder
public record CreateCraftingRequestOutput(ACraftingRequest craftingRequest) {
}
