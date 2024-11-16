package com.cplerings.core.api.crafting.data;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequest;

import lombok.Builder;

@Builder
public record CraftingRequest(ACraftingRequest craftingRequest) {
}