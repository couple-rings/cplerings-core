package com.cplerings.core.application.craftingrequest.input;

import lombok.Builder;

@Builder
public record CreateCraftingRequestInput(Long customerId, Long metalSpecId, Long diamondSpecId, Long customDesignId,
                                         String engraving, Integer fingerSize) {
}
