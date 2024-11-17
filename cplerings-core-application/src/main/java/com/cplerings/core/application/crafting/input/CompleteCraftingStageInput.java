package com.cplerings.core.application.crafting.input;

import com.cplerings.core.application.crafting.input.data.RingMaintenance;

import lombok.Builder;

import java.util.Set;

@Builder
public record CompleteCraftingStageInput(Long craftingStageId, Long imageId,
                                         Set<RingMaintenance> ringMaintenances) {

}
