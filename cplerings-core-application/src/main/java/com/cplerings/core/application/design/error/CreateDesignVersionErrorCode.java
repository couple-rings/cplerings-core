package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CreateDesignVersionErrorCode implements ErrorCode {

    DESIGN_FILE_REQUIRED("002", "design.version.error.fileRequired", ErrorCode.Type.VALIDATION),
    IMAGE_REQUIRED("003", "design.version.error.imageRequired", ErrorCode.Type.VALIDATION),
    VERSION_NUMBER_WRONG_POSITIVE_NUMBER("004", "design.version.error.invalidPositiveVersionNumber", ErrorCode.Type.VALIDATION),
    DESIGN_ID_WRONG_POSITIVE_NUMBER("005", "design.version.error.invalidPositiveDesignId", ErrorCode.Type.VALIDATION),
    INVALID_DESIGN_ID("006", "design.version.error.invalidDesignId", ErrorCode.Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
