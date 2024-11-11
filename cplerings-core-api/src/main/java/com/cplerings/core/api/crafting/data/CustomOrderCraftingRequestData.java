package com.cplerings.core.api.crafting.data;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequest;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;

import lombok.Builder;

@Builder
public record CustomOrderCraftingRequestData(ACustomOrder customOrder, ACraftingRequest firstCraftingRequest,
                                             ACraftingRequest secondCraftingRequest) {
}
