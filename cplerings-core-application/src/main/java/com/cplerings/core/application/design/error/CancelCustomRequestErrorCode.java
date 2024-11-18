package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CancelCustomRequestErrorCode implements ErrorCode {

    CUSTOM_REQUEST_ID_REQUIRED("002", "cancelCustomRequest.error.customRequestIdRequired", Type.VALIDATION),
    CUSTOM_REQUEST_ID_WRONG_POSITIVE_NUMBER("003", "cancelCustomRequest.error.customRequestIdWrongPositiveNumber", ErrorCode.Type.VALIDATION),
    INVALID_CUSTOM_REQUEST_ID("004", "cancelCustomRequest.error.customRequestIdNotReal", ErrorCode.Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
