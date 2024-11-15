package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewCustomOrderErrorCode implements ErrorCode {

    CUSTOM_ORDER_ID_REQUIRED("002", "viewCustomOrder.error.customOrderIdRequired", Type.VALIDATION),
    CUSTOM_ORDER_ID_WRONG_POSITIVE_INTEGER("003", "viewCustomOrder.error.customOrderIdWrongPositiveInteger", Type.VALIDATION),
    INVALID_CUSTOM_ORDER("004", "viewCustomOrder.error.invalidCustomOrderId", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
