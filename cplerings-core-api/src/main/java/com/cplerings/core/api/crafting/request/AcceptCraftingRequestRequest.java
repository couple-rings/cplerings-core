package com.cplerings.core.api.crafting.request;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequestStatus;
import com.cplerings.core.application.shared.entity.order.ADifficulty;

import lombok.Builder;

@Builder
public record AcceptCraftingRequestRequest(Long firstCraftingRequestId, Long secondCraftingRequestId,
                                           ACraftingRequestStatus status, String firstCommentCrafting,
                                           String secondCommentCrafting, ADifficulty difficulty) {

}
