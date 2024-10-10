package com.cplerings.core.application.verification.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResendVerificationErrorCode implements ErrorCode {

    EMAIL_REQUIRED("002", "resend.error.emailRequired", Type.VALIDATION),
    ACCOUNT_WITH_EMAIL_NOT_FOUND("003", "resend.error.accountWithEmailNotFound", Type.BUSINESS),
    ACCOUNT_NOT_IN_VERIFYING("004", "resend.error.accountNotInVerifying", Type.BUSINESS);

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
