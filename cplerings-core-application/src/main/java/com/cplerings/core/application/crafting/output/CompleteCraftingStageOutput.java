package com.cplerings.core.application.crafting.output;

import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;

import lombok.Builder;

@Builder
public record CompleteCraftingStageOutput(ACraftingStage craftingStage) {

}
