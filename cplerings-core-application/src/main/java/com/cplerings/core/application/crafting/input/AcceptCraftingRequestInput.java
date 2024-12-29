package com.cplerings.core.application.crafting.input;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequestStatus;
import com.cplerings.core.application.shared.entity.order.ADifficulty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcceptCraftingRequestInput {

    private Long firstCraftingRequestId;
    private Long secondCraftingRequestId;
    private ACraftingRequestStatus status;
    private String firstCommentCrafting;
    private String secondCommentCrafting;
    private ADifficulty firstCraftingRequestDifficulty;
    private ADifficulty secondCraftingRequestDifficulty;
}
