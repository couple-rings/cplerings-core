package com.cplerings.core.application.crafting.input;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequestStatus;

import lombok.Builder;

@Builder
public record AcceptCraftingRequestInput(Long firstCraftingRequestId, Long secondCraftingRequestId,
                                         ACraftingRequestStatus status, String firstCommentCrafting,
                                         String secondCommentCrafting, Long branchId) {
}
