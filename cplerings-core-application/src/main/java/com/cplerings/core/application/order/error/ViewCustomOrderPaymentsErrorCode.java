package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ViewCustomOrderPaymentsErrorCode implements ErrorCode {

    CUSTOM_ORDER_ID_REQUIRED("002", "viewCustomOrderPayments.error.customOrderIdRequired", Type.VALIDATION),
    INVALID_CUSTOM_ORDER_ID("003", "viewCustomOrderPayments.error.invalidCustomOrderId", Type.VALIDATION),
    CUSTOM_ORDER_NOT_FOUND("004", "viewCustomOrderPayments.error.customOrderNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
