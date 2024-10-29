package com.cplerings.core.application.shared.usecase;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public final class UseCaseValidator {

    private final Collection<ErrorCode> storedErrorCodes = new ArrayList<>();

    public void validate(boolean validCase, ErrorCode errorCode) {
        Objects.requireNonNull(errorCode);
        if (validCase) {
            return;
        }
        storedErrorCodes.add(errorCode);
    }

    public void validateAndStopExecution(boolean validCase, ErrorCode errorCode) {
        Objects.requireNonNull(errorCode, "Error code must not be null");
        if (validCase) {
            return;
        }
        storedErrorCodes.add(errorCode);
        clearAndThrowErrorCodes();
    }

    public void clearAndThrowErrorCodes() {
        if (CollectionUtils.isNotEmpty(storedErrorCodes)) {
            final ErrorCodes errors = ErrorCodes.create(storedErrorCodes);
            storedErrorCodes.clear();
            throw new ErrorCodeException(errors);
        }
    }

    public void submitErrorCodesAndThrow(ErrorCodes errorCodes) {
        Objects.requireNonNull(errorCodes);
        final Collection<ErrorCode> finalErrorCodes = new ArrayList<>(storedErrorCodes);
        storedErrorCodes.clear();
        finalErrorCodes.addAll(errorCodes.getErrors());
        throw new ErrorCodeException(ErrorCodes.create(finalErrorCodes));
    }
}
