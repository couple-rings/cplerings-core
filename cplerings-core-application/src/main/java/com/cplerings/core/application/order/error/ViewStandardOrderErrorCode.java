package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ViewStandardOrderErrorCode implements ErrorCode {

    STANDARD_ORDER_ID_REQUIRED("002", "viewStandardOrderDetail.error.standardOrderIdRequired", Type.VALIDATION),
    STANDARD_ORDER_ID_WRONG_INTEGER("003", "viewStandardOrderDetail.error.standardOrderIdWrongInteger", Type.VALIDATION),
    STANDARD_ORDER_NOT_FOUND("004", "viewStandardOrderDetail.error.standardOrderNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
