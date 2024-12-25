package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResellCustomOrderErrorCode implements ErrorCode {

    CUSTOM_ORDER_ID_REQUIRED("002", "resellCustomOrder.error.customOrderIdRequired", Type.VALIDATION),
    INVALID_CUSTOM_ORDER_ID("003", "resellCustomOrder.error.invalidCustomOrderId", Type.VALIDATION),
    CUSTOM_ORDER_NOT_FOUND("004", "resellCustomOrder.error.customOrderNotFound", Type.BUSINESS),
    CUSTOM_ORDER_NOT_COMPLETE("005", "resellCustomOrder.error.customOrderNotComplete", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
