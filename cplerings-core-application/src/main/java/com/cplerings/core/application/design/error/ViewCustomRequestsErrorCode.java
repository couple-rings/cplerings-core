package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewCustomRequestsErrorCode implements ErrorCode {

    INVALID_STATUS("002", "customrequest.error.invalidStatus", Type.BUSINESS),
    CUSTOMER_ID_WRONG_POSITIVE_INTEGER("003", "customrequest.error.invalidCustomerIdPositiveInteger", Type.VALIDATION),
    INVALID_CUSTOMER_ID("004", "customrequest.error.invalidCustomerId", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
