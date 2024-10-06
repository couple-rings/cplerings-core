package com.cplerings.core.application.account.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountErrorCode implements ErrorCode {

    EMAIL_REQUIRED("002", "account.error.emailRequired", Type.VALIDATION),
    PASSWORD_REQUIRED("003", "account.error.passwordRequired", Type.VALIDATION),
    USERNAME_REQUIRED("004", "account.error.usernameRequired", Type.VALIDATION),
    EMAIL_ALREADY_REGISTERED("005", "account.error.emailAlreadyRegistered", Type.BUSINESS),
    USERNAME_ALREADY_REGISTERED("006", "account.error.usernameAlreadyRegistered", Type.BUSINESS),
    ACCOUNT_NOT_IN_VERIFYING_STATE("007", "account.error.notInVerifyingState", Type.BUSINESS),;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
