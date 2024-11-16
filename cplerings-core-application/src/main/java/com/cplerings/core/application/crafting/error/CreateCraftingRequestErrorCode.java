package com.cplerings.core.application.crafting.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CreateCraftingRequestErrorCode implements ErrorCode {

    CUSTOMER_ID_WRONG_POSITIVE_INTEGER("002", "createCraftingRequest.error.customerIdWrongPositiveNumber", Type.VALIDATION),
    CUSTOM_DESIGN_ID_WRONG_POSITIVE_INTEGER("003", "createCraftingRequest.error.customDesignIdWrongPositiveNumber", Type.VALIDATION),
    DIAMOND_SPECIFICATION_ID_WRONG_POSITIVE_INTEGER("004", "createCraftingRequest.error.diamondSpecIdWrongPositiveNumber", Type.VALIDATION),
    METAL_SPECIFICATION_ID_WRONG_POSITIVE_INTEGER("005", "createCraftingRequest.error.metalSpecIdWrongPositiveNumber", Type.VALIDATION),
    FINGER_SIZE_WRONG_POSITIVE_INTEGER("006", "createCraftingRequest.error.fingerSizeWrongPositiveNumber", Type.VALIDATION),
    DIAMOND_SPECIFICATION_NOT_FOUND("007", "createCraftingRequest.error.diamondSpecificationNotFound", Type.VALIDATION),
    CUSTOM_DESIGN_NOT_FOUND("008", "createCraftingRequest.error.customDesignNotFound", Type.VALIDATION),
    METAL_SPECIFICATION_NOT_FOUND("009", "createCraftingRequest.error.metalSpecificationNotFound", Type.VALIDATION),
    BRANCH_ID_REQUIRED("010", "createCraftingRequest.error.branchIdRequired", Type.VALIDATION),
    INVALID_BRANCH_ID("011", "createCraftingRequest.error.invalidBranchId", Type.VALIDATION),
    CUSTOMER_NOT_FOUND("012", "createCraftingRequest.error.customerNotFound", Type.VALIDATION),
    BRANCH_NOT_FOUND("013", "createCraftingRequest.error.branchNotFound", Type.VALIDATION),
    MAX_ALLOWED_CRAFTING_REQUESTS_EXCEEDED("014", "createCraftingRequest.error.maxAllowedCraftingRequestsExceeded", Type.BUSINESS),
    DIFFERENT_BRANCH_ID_FROM_PREVIOUS_CRAFTING_REQUEST("015", "createCraftingRequest.error.differentBranchIdFromPreviousCraftingRequest", Type.BUSINESS);

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
