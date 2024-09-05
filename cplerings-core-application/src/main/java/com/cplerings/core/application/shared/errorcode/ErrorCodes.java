package com.cplerings.core.application.shared.errorcode;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorCodes {

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
}
