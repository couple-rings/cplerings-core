package com.cplerings.core.application.transport.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UpdateTransportationOrderStatusErrorCode implements ErrorCode {

    TRANSPORTATION_ORDER_ID_REQUIRED("002", "updateTransportOrdersStatus.error.transportationOrderIdRequired", Type.VALIDATION),
    STATUS_REQUIRED("003", "updateTransportOrdersStatus.error.statusRequired", Type.VALIDATION),
    TRANSPORTATION_ORDER_ID_WRONG_POSITIVE_NUMBER("004", "updateTransportOrdersStatus.error.transportationOrderWrongPositiveNumber", Type.VALIDATION),
    INVALID_TRANSPORTATION_ORDER_ID("005", "updateTransportOrdersStatus.error.invalidTransportationOrderId", Type.BUSINESS),
    INVALID_STATUS("006", "updateTransportOrdersStatus.error.invalidStatus", Type.VALIDATION),
    WRONG_STATUS("007", "updateTransportOrdersStatus.error.wrongStatus", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
