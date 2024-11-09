package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DetermineCustomRequestErrorCode implements ErrorCode {

    CUSTOM_REQUEST_ID_REQUIRED("002", "determine.customrequest.error.customRequestIdIsRequired", Type.VALIDATION),
    STAFF_ID_REQUIRED("003", "determine.customrequest.error.commentIsrequired", Type.VALIDATION),
    CUSTOM_REQUEST_ID_WRONG_POSITIVE_INTEGER("004", "determine.customrequest.error.customRequestIdWrongPositiveInteger", Type.VALIDATION),
    WRONG_STATUS("005", "determine.customrequest.error.invalidStatus", Type.VALIDATION),
    INVALID_CUSTOM_REQUEST_ID("006", "determine.customrequest.error.invalidCustomRequestId", Type.BUSINESS),
    INVALID_CUSTOM_REQUEST_STATUS("007", "determine.customrequest.error.wrongStatusForDetermination", Type.BUSINESS),
    INVALID_STAFF_ID("008", "determine.customrequest.error.invalidStaffId", Type.BUSINESS)
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
