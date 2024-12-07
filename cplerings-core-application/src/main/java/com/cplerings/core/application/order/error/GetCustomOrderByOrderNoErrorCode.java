package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GetCustomOrderByOrderNoErrorCode implements ErrorCode {

    ORDER_NO_BLANK("002", "getCustomOrderByOrderNo.error.orderNoBlank", Type.VALIDATION),
    CUSTOM_ORDER_NOT_FOUND("003", "getCustomOrderByOrderNo.error.customOrderNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
