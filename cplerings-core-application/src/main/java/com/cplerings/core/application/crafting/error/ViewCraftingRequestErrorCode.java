package com.cplerings.core.application.crafting.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewCraftingRequestErrorCode implements ErrorCode {

    WRONG_ID_POSITIVE_INTEGER("002", "viewCraftingRequest.error.IdMustBePositiveInteger", Type.VALIDATION),
    INVALID_CRAFTING_REQUEST_ID("003", "viewCraftingRequest.error.invalidCraftingRequestId", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
