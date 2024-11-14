package com.cplerings.core.application.spouse.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewSpousesOfCustomerErrorCode implements ErrorCode {

    CUSTOMER_ID_REQUIRED("002", "viewSpouseOfCustomer.error.customerIdRequired", ErrorCode.Type.VALIDATION),
    CUSTOMER_ID_WRONG_POSITIVE_NUMBER("003", "viewSpouseOfCustomer.error.customerIdWrongPositiveNumber", ErrorCode.Type.VALIDATION),
    INVALID_CUSTOMER_ID("004", "viewSpouseOfCustomer.error.invalidAccountId", ErrorCode.Type.BUSINESS),
    NOT_A_CUSTOMER("005", "viewSpouseOfCustomer.error.notACustomer", ErrorCode.Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
