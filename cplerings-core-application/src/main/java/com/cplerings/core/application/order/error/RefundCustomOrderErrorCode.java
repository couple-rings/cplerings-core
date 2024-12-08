package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum RefundCustomOrderErrorCode implements ErrorCode {

    CUSTOM_ORDER_ID_REQUIRED("002", "refundCustomOrder.error.customOrderIdRequired", Type.VALIDATION),
    REFUND_DETAIL_REQUIRED("003", "refundCustomOrder.error.refundDetailRequired", Type.VALIDATION),
    STAFF_ID_REQUIRED("004", "refundCustomOrder.error.staffIdRequired", Type.VALIDATION),
    REASON_REQUIRED("005", "refundCustomOrder.error.reasonRequired", Type.VALIDATION),
    PROOF_IMAGE_ID_REQUIRED("006", "refundCustomOrder.error.proofImageIdRequired", Type.VALIDATION),
    REFUND_METHOD_REQUIRED("007", "refundCustomOrder.error.refundMethodRequired", Type.VALIDATION),
    INVALID_CUSTOM_ORDER_ID("008", "refundCustomOrder.error.invalidCustomOrderId", Type.VALIDATION),
    INVALID_STAFF_ID("009", "refundCustomOrder.error.invalidStaffId", Type.VALIDATION),
    INVALID_PROOF_IMAGE_ID("010", "refundCustomOrder.error.invalidProofImageId", Type.VALIDATION),
    CUSTOM_ORDER_NOT_FOUND("011", "refundCustomOrder.error.customOrderNotFound", Type.BUSINESS),
    CUSTOM_ORDER_NOT_COMPLETED("012", "refundCustomOrder.error.customOrderNotCompleted", Type.BUSINESS),
    CUSTOM_ORDER_NOT_RECEIVED_BY_CUSTOMER("013", "refundCustomOrder.error.customOrderNotReceivedByCustomer", Type.BUSINESS),
    STAFF_NOT_FOUND("014", "refundCustomOrder.error.staffNotFound", Type.BUSINESS),
    PROOF_IMAGE_NOT_FOUND("015", "refundCustomOrder.error.proofImageNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
