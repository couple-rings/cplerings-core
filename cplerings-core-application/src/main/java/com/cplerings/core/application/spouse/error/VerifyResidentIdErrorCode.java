package com.cplerings.core.application.spouse.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VerifyResidentIdErrorCode implements ErrorCode {

    CITIZEN_ID_REQUIRED("002", "verifySpouse.error.citizenIdRequired", ErrorCode.Type.VALIDATION),
    SPOUSE_NOT_FOUND_WITH_CITIZEN_ID("003", "verifySpouse.error.citizenIdNotFound", Type.BUSINESS),
    CUSTOMER_ID_REQUIRED("004", "verifySpouse.error.customerIdRequired", Type.VALIDATION),
    WRONG_CUSTOMER_ID_INTEGER("005", "verifySpouse.error.customerIdWrongInteger", Type.VALIDATION),
    CUSTOMER_NOT_FOUND("006", "verifySpouse.error.customerNotFound", Type.BUSINESS),
    NOT_RIGHT_CUSTOMER("007", "verifySpouse.error.notRightCustomer", Type.BUSINESS),
    SPOUSE_ACCOUNT_NULL("008", "verifySpouse.error.spouseAccountNull", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
