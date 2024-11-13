package com.cplerings.core.application.transport.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AssignTransportOrderErrorCode implements ErrorCode {

    TRANSPORT_ORDER_ID_REQUIRED("002", "assignTransportOrder.error.transportOrderIdRequired", ErrorCode.Type.VALIDATION),
    TRANSPORT_ORDER_ID_WRONG_POSITIVE_NUMBER("003", "assignTransportOrder.error.transportOrderIdWrongPositiveInteger", ErrorCode.Type.VALIDATION),
    TRANSPORTER_ID_REQUIRED("004", "assignTransportOrder.error.transporterIdRequired", ErrorCode.Type.VALIDATION),
    TRANSPORTER_ID_WRONG_POSITIVE_NUMBER("005", "assignTransportOrder.error.transporterWrongPositiveInteger", ErrorCode.Type.VALIDATION),
    INVALID_TRANSPORTER_ID("005", "assignTransportOrder.error.invalidTransporter", Type.BUSINESS),
    INVALID_TRANSPORTATION_ORDER_ID("006", "assignTransportOrder.error.invalidTransportationOrderId", ErrorCode.Type.BUSINESS),
    INVALID_TRANSPORTATION_ORDER_STATUS_FOR_ASSIGNING("007", "assignTransportOrder.error.wrongTransportOrderStatus", ErrorCode.Type.BUSINESS),
    TRANSPORTATION_ORDER_HAS_BEEN_ASSIGNED("008", "assignTransportOrder.error.transportOrderHasBeenAssigned", ErrorCode.Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
