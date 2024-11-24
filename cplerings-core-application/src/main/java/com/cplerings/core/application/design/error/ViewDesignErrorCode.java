package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ViewDesignErrorCode implements ErrorCode {

    DESIGN_ID_REQUIRED("002", "viewDesign.error.designIdRequired", Type.VALIDATION),
    WRONG_DESIGN_ID_POSITIVE_INTEGER("003", "viewDesign.error.designIdWrongPositiveInteger", Type.VALIDATION),
    DESIGN_NOT_FOUND("004", "viewDesign.error.designNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
