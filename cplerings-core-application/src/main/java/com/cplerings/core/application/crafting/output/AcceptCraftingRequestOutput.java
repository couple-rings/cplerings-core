package com.cplerings.core.application.crafting.output;

import com.cplerings.core.application.shared.entity.craftingrequest.ACraftingRequest;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;

import lombok.Builder;

@Builder
public record AcceptCraftingRequestOutput(ACustomOrder customOrder, ACraftingRequest firstCraftingRequest,
                                          ACraftingRequest secondCraftingRequest) {
}
