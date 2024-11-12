package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewCustomDesignErrorCode implements ErrorCode {

    CUSTOM_DESIGN_ID_REQUIRED("002", "viewCustomDesign.error.customDesignIdRequired", Type.VALIDATION),
    CUSTOM_DESIGN_ID_WRONG_POSITIVE_NUMBER("003", "viewCustomDesign.error.customDesignIdWrongPositiveNumber", Type.VALIDATION),
    INVALID_CUSTOM_DESIGN_ID("004", "viewCustomDesign.error.invalidId", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
