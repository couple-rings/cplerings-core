package com.cplerings.core.application.authentication.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthenticationErrorCode implements ErrorCode {

    NO_EMAIL("002", "authentication.error.emailRequired", Type.VALIDATION),
    NO_PASSWORD("003", "authentication.error.passwordRequired", Type.VALIDATION),
    ACCOUNT_WITH_EMAIL_NOT_FOUND("004", "authentication.error.accountWithEmailNotFound", Type.BUSINESS),
    INVALID_PASSWORD("005", "authentication.error.invalidPassword", Type.BUSINESS),
    NO_TOKEN("006", "authentication.error.tokenRequired", Type.VALIDATION),
    INVALID_TOKEN("007", "authentication.error.invalidToken", Type.VALIDATION),
    TOKEN_EXPIRED("008", "authentication.error.tokenExpired", Type.VALIDATION),
    UNAUTHORIZED("009", "authentication.error.unauthorized", Type.VALIDATION),
    INVALID_ACCOUNT_FROM_TOKEN("010", "authentication.error.invalidAccountFromToken", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
