package com.cplerings.core.application.shared.usecase;

import com.cplerings.core.application.shared.errorcode.ErrorCodes;

import lombok.Getter;

@Getter
public final class ErrorCodeException extends RuntimeException {

    private final ErrorCodes errorCodes;

    public ErrorCodeException(ErrorCodes errorCodes) {
        super(errorCodes.toString());
        this.errorCodes = errorCodes;
    }
}
