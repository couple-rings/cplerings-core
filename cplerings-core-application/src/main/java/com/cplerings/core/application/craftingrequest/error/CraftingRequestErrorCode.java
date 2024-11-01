package com.cplerings.core.application.craftingrequest.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CraftingRequestErrorCode implements ErrorCode {

    CUSTOMER_ID_WRONG_POSITIVE_INTEGER("002", "crafting.request.customerIdWrongPositiveNumber", Type.VALIDATION),
    CUSTOM_DESIGN_ID_WRONG_POSITIVE_INTEGER("002", "crafting.request.customerIdWrongPositiveNumber", Type.VALIDATION),
    DIAMOND_SPECIFICATION_ID_WRONG_POSITIVE_INTEGER("002", "crafting.request.customerIdWrongPositiveNumber", Type.VALIDATION),
    METAL_SPECIFICATION_ID_WRONG_POSITIVE_INTEGER("002", "crafting.request.customerIdWrongPositiveNumber", Type.VALIDATION),
    FINGER_SIZE_WRONG_POSITIVE_INTEGER("002", "crafting.request.customerIdWrongPositiveNumber", Type.VALIDATION),
    INVALID_DIAMOND_SPECIFICATION_ID("002", "crafting.request.customerIdWrongPositiveNumber", Type.VALIDATION),
    INVALID_CUSTOM_DESIGN_ID("002", "crafting.request.customerIdWrongPositiveNumber", Type.VALIDATION),
    INVALID_METAL_SPECIFICATION_ID("002", "crafting.request.customerIdWrongPositiveNumber", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
