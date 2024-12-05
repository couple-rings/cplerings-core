package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PayStandardOrderErrorCode implements ErrorCode {

    STANDARD_ORDER_ID_REQUIRED("002", "payStandardOrder.error.standardOrderIdRequired", ErrorCode.Type.VALIDATION),
    INVALID_STANDARD_ORDER_ID("003", "payStandardOrder.error.invalidStandardOrderId", ErrorCode.Type.VALIDATION),
    STANDARD_ORDER_NOT_FOUND("004", "payStandardOrder.error.standardOrderNotFound", ErrorCode.Type.VALIDATION),
    JEWELRY_NOT_IN_STOCK("005", "payStandardOrder.error.jewelryNotInStock", Type.BUSINESS),
    ADDRESS_NOT_FOUND("006", "payStandardOrder.error.transportationAddressNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
