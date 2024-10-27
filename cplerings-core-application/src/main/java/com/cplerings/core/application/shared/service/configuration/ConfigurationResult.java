package com.cplerings.core.application.shared.service.configuration;

import lombok.Builder;

@Builder
public class ConfigurationResult<T> {

    private T result;

    public T getResult() {
        if (result == null) {
            throw new IllegalStateException("Result is null");
        }
        return result;
    }
}
