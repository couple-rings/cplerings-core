package com.cplerings.core.application.shared.usecase;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public final class UseCaseValidator {

    private final Collection<ErrorCode> errorCodes = new ArrayList<>();

    public void validate(boolean validCase, ErrorCode errorCode) {
        Objects.requireNonNull(errorCode);
        if (validCase) {
            return;
        }
        errorCodes.add(errorCode);
    }

    public void validateAndStopExecution(boolean validCase, ErrorCode errorCode) {
        Objects.requireNonNull(errorCode);
        if (validCase) {
            return;
        }
        errorCodes.add(errorCode);
        clearAndThrowErrorCodes();
    }

    public void clearAndThrowErrorCodes() {
        if (CollectionUtils.isNotEmpty(errorCodes)) {
            final ErrorCodes errors = ErrorCodes.create(errorCodes);
            errorCodes.clear();
            throw new ErrorCodeException(errors);
        }
    }
}
