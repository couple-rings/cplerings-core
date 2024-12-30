package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CancelCustomOrderErrorCode implements ErrorCode {

    CUSTOM_ORDER_ID_REQUIRED("002", "cancelCustomOrder.error.customOrderIdRequired", Type.VALIDATION),
    INVALID_CUSTOM_ORDER_ID("003", "cancelCustomOrder.error.invalidCustomOrderId", Type.VALIDATION),
    CUSTOM_ORDER_NOT_FOUND("004", "cancelCustomOrder.error.customOrderNotFound", Type.BUSINESS),
    CUSTOM_ORDER_ALREADY_CANCELLED("005", "cancelCustomOrder.error.customOrderAlreadyCancelled", Type.BUSINESS),
    CUSTOM_ORDER_ALREADY_COMPLETED("006", "cancelCustomOrder.error.customOrderAlreadyCompleted", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
