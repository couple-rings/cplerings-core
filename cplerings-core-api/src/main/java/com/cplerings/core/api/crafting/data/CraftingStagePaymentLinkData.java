package com.cplerings.core.api.crafting.data;

import lombok.Builder;

@Builder
public record CraftingStagePaymentLinkData(Long paymentId, String paymentLink) {

}
