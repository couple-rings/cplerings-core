package com.cplerings.core.application.crafting.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CompleteCraftingStageErrorCode implements ErrorCode {

    CRAFTING_STAGE_ID_REQUIRED("002", "completeCraftingStage.error.craftingStageIdRequired", Type.VALIDATION),
    INVALID_CRAFTING_STAGE_ID("003", "completeCraftingStage.error.invalidCraftingStageId", Type.VALIDATION),
    IMAGE_ID_REQUIRED("004", "completeCraftingStage.error.imageIdRequired", Type.VALIDATION),
    INVALID_IMAGE_ID("005", "completeCraftingStage.error.invalidImageId", Type.VALIDATION),
    CRAFTING_STAGE_NOT_FOUND("006", "completeCraftingStage.error.craftingStageNotFound", Type.VALIDATION),
    CRAFTING_STAGE_ALREADY_COMPLETED("007", "completeCraftingStage.error.craftingStageAlreadyCompleted", Type.BUSINESS),
    CRAFTING_STAGE_IS_NOT_PAID("008", "completeCraftingStage.error.craftingStageIsNotPaid", Type.BUSINESS),
    IMAGE_NOT_FOUND("009", "completeCraftingStage.error.imageNotFound", Type.VALIDATION),
    PREVIOUS_CRAFTING_STAGE_NOT_PAID("010", "completeCraftingStage.error.previousCraftingStageNotPaid", Type.BUSINESS),
    PREVIOUS_CRAFTING_STAGE_NOT_COMPLETED("011", "completeCraftingStage.error.previousCraftingStageNotCompleted", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
