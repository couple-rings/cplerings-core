package com.cplerings.core.application.crafting.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum DepositCraftingStageErrorCode implements ErrorCode {

    CRAFTING_STAGE_ID_REQUIRED("002", "depositCraftingStage.error.craftingStageIdRequired", Type.VALIDATION),
    INVALID_CRAFTING_STAGE_ID("003", "depositCraftingStage.error.invalidCraftingStageId", Type.VALIDATION),
    CRAFTING_STAGE_NOT_FOUND("004", "depositCraftingStage.error.craftingStageNotFound", Type.VALIDATION),
    CRAFTING_STAGE_NOT_IN_PENDING("005", "depositCraftingStage.error.craftingStageNotInPending", Type.BUSINESS),
    CUSTOM_ORDER_STATUS_NOT_VALID("006", "depositCraftingStage.error.customOrderStatusNotValid", Type.BUSINESS),
    PREVIOUS_STAGE_NOT_PAID("007", "depositCraftingStage.error.previousStageNotPaid", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
