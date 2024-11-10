package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DetermineDesignVersionErrorCode implements ErrorCode {

    DESIGN_VERSION_ID_WRONG_POSITIVE_NUMBER("002", "determine.design-version.error.designVersionIdWrongInteger", Type.VALIDATION),
    INVALID_DESIGN_VERSION_ID("003", "determine.design-version.error.designVersionIdIsNotReal", Type.BUSINESS),
    DESIGN_VERSION_ID_REQUIRED("004", "determine.design-version.error.designVersionIdRequired", Type.VALIDATION),
    HAS_BEEN_ACCEPTED("005", "determine.design-version.error.designVersionHasBeenAccepted", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
