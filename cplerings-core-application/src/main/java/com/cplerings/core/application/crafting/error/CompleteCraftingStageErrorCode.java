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
    RING_MAINTENANCES_REQUIRED("012", "completeCraftingStage.error.ringMaintenancesRequired", Type.VALIDATION),
    TWO_RING_MAINTENANCES_REQUIRED("013", "completeCraftingStage.error.twoRingMaintenancesRequired", Type.VALIDATION),
    INVALID_RINGS("014", "completeCraftingStage.error.invalidRings", Type.VALIDATION),
    INVALID_MAINTENANCES("015", "completeCraftingStage.error.invalidMaintenances", Type.VALIDATION),
    RINGS_NOT_FOUND("016", "completeCraftingStage.error.ringsNotFound", Type.VALIDATION),
    MAINTENANCES_NOT_FOUND("017", "completeCraftingStage.error.maintenancesNotFound", Type.VALIDATION),
    RINGS_NOT_PART_OF_CUSTOM_ORDER("018", "completeCraftingStage.error.ringsNotPartOfCustomOrder", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
