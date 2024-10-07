package com.cplerings.core.application.shared.errorcode;

import static com.cplerings.core.application.shared.errorcode.ErrorCode.System.ERROR;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorCodes {

    public static final ErrorCodes SYSTEM_ERROR = ErrorCodes.create(ERROR);

    private final Collection<ErrorCode> errors;

    public static ErrorCodes create(Collection<ErrorCode> errors) {
        if (CollectionUtils.isEmpty(errors)) {
            throw new IllegalArgumentException("Errors is empty");
        }
        return new ErrorCodes(errors.stream()
                .filter(Objects::nonNull)
                .sorted(new ErrorCodeComparator())
                .toList());
    }

    public static ErrorCodes create(ErrorCode... errors) {
        if (ArrayUtils.isEmpty(errors)) {
            throw new IllegalArgumentException("Errors is empty");
        }
        return new ErrorCodes(Arrays.stream(errors)
                .filter(Objects::nonNull)
                .sorted(new ErrorCodeComparator())
                .toList());
    }
}
