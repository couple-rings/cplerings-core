package com.cplerings.core.api;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import com.cplerings.core.api.mapper.ErrorCodeResponseMapper;
import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public final class ErrorCodesResponse extends AbstractResponse<ErrorCode> {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Collection<ErrorCodeResponse> errors;

    public static ErrorCodesResponse create(ErrorCodes errorCodes) {
        Objects.requireNonNull(errorCodes, "Error codes cannot be null");
        return ErrorCodesResponse.builder()
                .type(Type.ERROR)
                .errors(errorCodes.getErrors().stream()
                        .map(ErrorCodeResponseMapper.INSTANCE::toResponse)
                        .sorted()
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .build();
    }
}
