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
    METAL_SPEC_DESIGN_IDS_REQUIRED("008", "createStandardOrder.error.metalSpecDesignIdsRequired", Type.VALIDATION),
    BRANCH_ID_REQUIRED("009", "createStandardOrder.error.branchIdRequired", Type.VALIDATION),
    BRANCH_ID_WRONG_INTEGER("010", "createStandardOrder.error.branchIdWrongInteger", Type.VALIDATION),
    JEWELRY_NOT_VALID("011", "createStandardOrder.error.jewelryNotValid", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
