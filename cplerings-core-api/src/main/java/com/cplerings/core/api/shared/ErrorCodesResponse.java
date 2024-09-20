package com.cplerings.core.api.shared;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import com.cplerings.core.api.mapper.ErrorCodeResponseMapper;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.temporal.TemporalUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ErrorCodesResponse {

    @Builder.Default
    private Instant timestamp = TemporalUtils.getCurrentInstantUTC();

    @Builder.Default
    private AbstractResponse.Type type = AbstractResponse.Type.ERROR;

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
