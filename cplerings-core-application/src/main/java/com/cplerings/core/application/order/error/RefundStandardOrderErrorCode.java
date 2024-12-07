package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RefundStandardOrderErrorCode implements ErrorCode {

    REFUND_DATA_REQUIRED("002", "refundStandardOrder.error.refundDataRequired", Type.VALIDATION),
    STANDARD_ORDER_ID_REQUIRED("003", "refundStandardOrder.error.standardOrderIdRequired", Type.VALIDATION),
    REASON_REQUIRED("004", "refundStandardOrder.error.reasonRequired", Type.VALIDATION),
    STAFF_ID_REQUIRED("005", "refundStandardOrder.error.staffIdRequired", Type.VALIDATION),
    IMAGE_ID_REQUIRED("006", "refundStandardOrder.error.imageIdRequired", Type.VALIDATION),
    STANDARD_ORDER_ID_WRONG_INTEGER("007", "refundStandardOrder.error.standardOrderIdWrongInteger", Type.VALIDATION),
    STAFF_ID_WRONG_INTEGER("008", "refundStandardOrder.error.staffIdWrongInteger", Type.VALIDATION),
    IMAGE_ID_WRONG_INTEGER("009", "refundStandardOrder.error.imageIdWrongInteger", Type.VALIDATION),
    STANDARD_ORDER_NOT_FOUND("010", "refundStandardOrder.error.standardOrderNotFound", Type.BUSINESS),
    WRONG_STATUS_FOR_REFUNDED("011", "refundStandardOrder.error.wrongStatusForRefunded", Type.BUSINESS),
    STAFF_NOT_FOUND("012", "refundStandardOrder.error.staffNotFound", Type.BUSINESS),
    IMAGE_NOT_FOUND("013", "refundStandardOrder.error.imageNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
