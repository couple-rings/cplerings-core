package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GetStandardOrderByOrderNoErrorCode implements ErrorCode {

    STANDARD_ORDER_NOT_FOUND("002", "getCustomOrderByOrderNo.error.standardOrderNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}