package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ViewDesignSessionsLeftErrorCode implements ErrorCode {

    CUSTOMER_ID_REQUIRED("002", "viewDesignSessionsLeft.error.customerIdRequired", Type.VALIDATION),
    WRONG_CUSTOMER_ID_POSITIVE_INTEGER("003", "viewDesignSessionsLeft.error.invalidCustomerIdPositiveInteger", Type.VALIDATION),
    INVALID_CUSTOMER_ID("004", "viewDesignSessionsLeft.error.invalidCustomerId", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
