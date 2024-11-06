package com.cplerings.core.api.craftingrequest.request;

import com.cplerings.core.application.shared.entity.craftingrequest.ACraftingRequestStatus;

import lombok.Builder;

@Builder
public record AcceptCraftingRequestRequest(Long firstCraftingRequestId, Long secondCraftingRequestId,
                                           ACraftingRequestStatus status, String firstCommentCrafting,
                                           String secondCommentCrafting, Long branchId) {
}
