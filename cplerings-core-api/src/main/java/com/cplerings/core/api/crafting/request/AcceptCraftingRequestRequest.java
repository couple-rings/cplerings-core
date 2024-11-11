package com.cplerings.core.api.crafting.request;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequestStatus;

import lombok.Builder;

@Builder
public record AcceptCraftingRequestRequest(Long firstCraftingRequestId, Long secondCraftingRequestId,
                                           ACraftingRequestStatus status, String firstCommentCrafting,
                                           String secondCommentCrafting, Long branchId) {
}
