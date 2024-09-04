package com.cplerings.core.application.authentication.error;

import com.cplerings.core.application.shared.usecase.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthenticationErrorCode implements ErrorCode {

    NO_EMAIL("001", "authentication.error.noEmail", Type.VALIDATION),
    NO_PASSWORD("002", "authentication.error.noPassword", Type.VALIDATION),
    ACCOUNT_WITH_EMAIL_NOT_FOUND("003", "authentication.error.accountWithEmailNotFound", Type.BUSINESS),
    INVALID_PASSWORD("004", "authentication.error.invalidPassword", Type.BUSINESS),
    INVALID_TOKEN("005", "authentication.error.invalidToken", Type.VALIDATION),
    TOKEN_EXPIRED("006", "authentication.error.tokenExpired", Type.VALIDATION);

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
