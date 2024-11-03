package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewDesignVersionErrorCode implements ErrorCode {

    DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER("002", "design.version.error.designVersionIdInvalidPositiveInteger", Type.VALIDATION),
    INVALID_DESIGN_VERSION_ID("003", "design.version.error.designVersionIdIsNotReal", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
