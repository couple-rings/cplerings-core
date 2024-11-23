package com.cplerings.core.application.transport.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GetTransportationOrderByCustomOrderErrorCode implements ErrorCode {

    CUSTOM_ORDER_ID_REQUIRED("002", "getTransportationOrderByCustomOrderId.error.customOrderIdRequired", Type.VALIDATION),
    CUSTOM_ORDER_ID_WRONG_POSITIVE_NUMBER("003", "getTransportationOrderByCustomOrderId.error.customOrderIdWrongPositiveNumber", Type.VALIDATION),
    TRANSPORTATION_ORDER_NOT_FOUND("004", "getTransportationOrderByCustomOrderId.error.transportationOrderNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
