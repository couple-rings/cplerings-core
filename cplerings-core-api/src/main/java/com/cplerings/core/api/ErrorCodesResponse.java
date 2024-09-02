package com.cplerings.core.api;

import java.util.Objects;

import com.cplerings.core.application.shared.usecase.ErrorCode;
import com.cplerings.core.application.shared.usecase.ErrorCodes;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public final class ErrorCodesResponse extends AbstractResponse<ErrorCode> {

    public static ErrorCodesResponse create(ErrorCodes errorCodes) {
        Objects.requireNonNull(errorCodes, "Error codes cannot be null");
        return ErrorCodesResponse.builder()
                .type(Type.ERROR)
                .errors(errorCodes.getErrors())
                .build();
    }
}
