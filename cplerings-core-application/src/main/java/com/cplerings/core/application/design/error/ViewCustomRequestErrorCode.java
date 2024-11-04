package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewCustomRequestErrorCode implements ErrorCode {

    INVALID_CUSTOM_REQUEST_ID("002", "customrequest.error.invalidId", Type.BUSINESS),
    WRONG_ID_POSITIVE_INTEGER("003", "customrequest.error.invalidPositiveInteger", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
