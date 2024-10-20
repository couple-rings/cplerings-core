package com.cplerings.core.application.payment.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentErrorCode implements ErrorCode {

    TERMINAL_CODE_REQUIRED("002", "payment.error.terminalCodeRequired", Type.VALIDATION),
    SECURE_HASH_REQUIRED("003", "payment.error.secureHashRequired", Type.VALIDATION),
    INVALID_PAYMENT_RESULT("004", "payment.error.invalidPaymentResult", Type.BUSINESS),
    PAYMENT_WITH_ID_NOT_FOUND("005", "payment.error.paymentWithIdNotFound", Type.BUSINESS),
    RESULT_CODE_REQUIRED("006", "payment.error.resultCodeRequired", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
