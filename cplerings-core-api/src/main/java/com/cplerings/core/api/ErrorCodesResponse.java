package com.cplerings.core.api;

import com.cplerings.core.api.mapper.ErrorCodeResponseMapper;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.temporal.TemporalUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public final class ErrorCodesResponse {

    @Builder.Default
    private String timestamp = String.valueOf(TemporalUtils.getCurrentInstantUTC().toEpochMilli());

    @Builder.Default
    private AbstractResponse.Type type = AbstractResponse.Type.ERROR;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Collection<ErrorCodeResponse> errors;

    public static ErrorCodesResponse create(ErrorCodes errorCodes) {
        Objects.requireNonNull(errorCodes, "Error codes cannot be null");
        return ErrorCodesResponse.builder()
                .errors(errorCodes.getErrors().stream()
                        .map(ErrorCodeResponseMapper.INSTANCE::toResponse)
                        .sorted()
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .build();
    }
}
