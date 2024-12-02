package com.cplerings.core.application.crafting.output;

import lombok.Builder;

@Builder
public record DepositCraftingStageOutput(Long paymentId, String paymentLink) {

}
