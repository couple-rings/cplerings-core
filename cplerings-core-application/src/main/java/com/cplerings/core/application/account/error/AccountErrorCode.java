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
    ACCOUNT_NOT_IN_VERIFYING_STATE("007", "account.error.notInVerifyingState", Type.BUSINESS),
    VERIFICATION_CODE_REQUIRED("008", "account.error.verificationCodeRequired", Type.VALIDATION),
    ACCOUNT_WITH_EMAIL_NOT_FOUND("009", "account.error.accountWithEmailNotFound", Type.BUSINESS),
    VERIFICATION_CODE_NOT_CREATED("010", "account.error.verificationCodeNotCreated", Type.BUSINESS),
    ACCOUNT_NOT_IN_ACTIVE_STATE("011", "account.error.notInActiveState", Type.BUSINESS),;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
