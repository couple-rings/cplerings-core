package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ProcessDesignSessionPaymentErrorCode implements ErrorCode {

    INVALID_ACCOUNT_ID("002", "processDesignSessionPayment.error.invalidAccountId", Type.VALIDATION),
    ACCOUNT_WITH_ID_NOT_FOUND("003", "processDesignSessionPayment.error.accountWithIdNotFound", Type.VALIDATION),
    ACCOUNT_NOT_ACTIVE("004", "processDesignSessionPayment.error.accountNotActive", Type.BUSINESS),
    DESIGN_SESSION_PAYMENT_NOT_FOUND("005", "processDesignSessionPayment.error.designSessionPaymentNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
