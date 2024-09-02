package com.cplerings.core.application.authentication.error;

import com.cplerings.core.application.shared.usecase.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthenticationErrorCode implements ErrorCode {

    NO_EMAIL("001", "authentication.error.noEmail", Type.VALIDATION),
    NO_PASSWORD("002", "authentication.error.noPassword", Type.VALIDATION),
    EMAIL_NOT_FOUND("003", "authentication.error.emailNotFound", Type.BUSINESS),
    INVALID_PASSWORD("004", "authentication.error.invalidPassword", Type.BUSINESS),;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
