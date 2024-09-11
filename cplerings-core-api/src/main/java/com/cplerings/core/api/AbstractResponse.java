package com.cplerings.core.api;

import com.cplerings.core.common.temporal.TemporalUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractResponse<T> {

    public enum Type {

        SINGLE, LIST, INFO, ERROR
    }

    public static final String ERROR_NAME = Type.ERROR.name();

    @Builder.Default
    private String timestamp = String.valueOf(TemporalUtils.getCurrentInstantUTC().toEpochMilli());

    private Type type;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
}
