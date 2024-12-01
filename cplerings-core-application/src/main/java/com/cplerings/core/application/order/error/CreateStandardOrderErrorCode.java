package com.cplerings.core.application.order.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CreateStandardOrderErrorCode implements ErrorCode {

    JEWELRIES_ID_REQUIRED("002", "createStandardOrder.error.jewelriesIdRequired", Type.VALIDATION),
    CUSTOMER_ID_REQUIRED("003", "createStandardOrder.error.customerIdRequired", Type.VALIDATION),
    CUSTOMER_ID_WRONG_INTEGER("004", "createStandardOrder.error.customerIdWrongInteger", Type.VALIDATION),
    CUSTOMER_NOT_FOUND("005", "createStandardOrder.error.customerNotFound", Type.BUSINESS),
    JEWELERY_NOT_FOUND("006", "createStandardOrder.error.jewelryNotFound", Type.BUSINESS),
    ADDRESS_NOT_FOUND("007", "createStandardOrder.error.addressNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
