package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DesignErrorCode implements ErrorCode {

    INVALID_CUSTOMER_ID("002", "design.session.error.invalidCustomerId", Type.VALIDATION),
    NOT_FOUND_CUSTOMER("003", "design.session.error.customerNotFound", Type.BUSINESS);

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
