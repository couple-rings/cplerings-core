package com.cplerings.core.application.payment.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GetPaymentErrorCode implements ErrorCode {

    PAYMENT_ID_REQUIRED("002", "getPayment.error.paymentIdRequired", Type.VALIDATION),
    PAYMENT_ID_WRONG_INTEGER("003", "getPayment.error.paymentIdWrongInteger", Type.VALIDATION),
    PAYMENT_NOT_FOUND("004", "getPayment.error.paymentNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
