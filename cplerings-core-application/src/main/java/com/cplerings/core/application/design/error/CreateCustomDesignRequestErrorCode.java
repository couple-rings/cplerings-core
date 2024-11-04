package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CreateCustomDesignRequestErrorCode implements ErrorCode {

    INVALID_CUSTOMER_ID("002", "createCustomDesignRequest.error.invalidCustomerId", ErrorCode.Type.VALIDATION),
    MUST_HAVE_TWO_DESIGNS("003", "createCustomDesignRequest.error.mustHaveTwoDesigns", ErrorCode.Type.VALIDATION),
    INVALID_DESIGN_ID("004", "createCustomDesignRequest.error.invalidDesignId", ErrorCode.Type.VALIDATION),
    DESIGN_NOT_AVAILABLE("005", "createCustomDesignRequest.error.designNotAvailable", Type.BUSINESS),
    CUSTOMER_NOT_FOUND("006", "createCustomDesignRequest.error.customerNotFound", ErrorCode.Type.VALIDATION),
    ACCOUNT_NOT_CUSTOMER("007", "createCustomDesignRequest.error.accountNotCustomer", ErrorCode.Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
