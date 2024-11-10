package com.cplerings.core.application.crafting.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ProcessCraftingStageDepositErrorCode implements ErrorCode {

    INVALID_CRAFTING_STAGE_ID("002", "processCraftingStageDeposit.error.invalidCraftingStageId", Type.VALIDATION),
    CRAFTING_STAGE_NOT_FOUND("003", "processCraftingStageDeposit.error.craftingStageNotFound", Type.VALIDATION),
    CRAFTING_STAGE_NOT_PENDING("004", "processCraftingStageDeposit.error.craftingStageNotPending", Type.BUSINESS),
    NO_CRAFTING_STAGES_IN_CUSTOM_ORDER("005", "processCraftingStageDeposit.error.noCraftingStagesInCustomOrder", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
