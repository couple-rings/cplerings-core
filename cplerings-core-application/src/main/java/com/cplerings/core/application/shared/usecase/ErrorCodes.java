package com.cplerings.core.application.shared.usecase;

import java.util.Collection;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

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
