package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompleteOrderErrorCode implements ErrorCode {

    ORDER_ID_REQUIRED("002", "completeOrder.error.orderIdRequired", Type.VALIDATION),
    ORDER_TYPE_REQUIRED("003", "completeOrder.error.orderTypeRequired", Type.VALIDATION),
    ORDER_ID_WRONG_INTEGER("004", "completeOrder.error.orderIdWrongInteger", Type.VALIDATION),
    CUSTOM_ORDER_NOTFOUND("005", "completeOrder.error.customOrderNotFound", Type.BUSINESS),
    STANDARD_ORDER_NOT_FOUND("006", "completeOrder.error.standardOrderNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
