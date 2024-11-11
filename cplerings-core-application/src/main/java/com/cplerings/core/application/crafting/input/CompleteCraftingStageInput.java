package com.cplerings.core.application.crafting.input;

import lombok.Builder;

@Builder
public record CompleteCraftingStageInput(Long craftingStageId, Long imageId) {

}
