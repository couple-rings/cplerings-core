package com.cplerings.core.application.transport.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewTransportationOrderErrorCode implements ErrorCode {

    TRANSPORTATION_ORDER_ID_REQUIRED("002", "viewTransportationOrder.error.transportationOrderIdRequired", Type.VALIDATION),
    TRANSPORTATION_ORDER_WRONG_INTEGER("003", "viewTransportationOrder.error.transportationOrderWrongInteger", Type.VALIDATION),
    INVALID_TRANSPORTATION_ORDER("004", "viewTransportationOrder.error.invalidTransportationOrder", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
