package com.cplerings.core.application.crafting.input;

import lombok.Builder;

@Builder
public record DepositCraftingStageInput(Long craftingStageId, Long transportationAddressId) {

}
