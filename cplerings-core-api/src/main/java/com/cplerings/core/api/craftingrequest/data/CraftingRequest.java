package com.cplerings.core.api.craftingrequest.data;

import com.cplerings.core.application.shared.entity.craftingrequest.ACraftingRequest;

import lombok.Builder;

@Builder
public record CraftingRequest(ACraftingRequest craftingRequest) {
}
