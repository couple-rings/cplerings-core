package com.cplerings.core.api.crafting.request;

import lombok.Builder;

@Builder
public record CreateCraftingRequestRequest(Long customerId, Long metalSpecId, Long diamondSpecId, Long customDesignId,
                                           String engraving, Integer fingerSize) {
}