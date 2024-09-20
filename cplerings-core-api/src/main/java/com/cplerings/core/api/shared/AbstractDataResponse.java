package com.cplerings.core.api.shared;

import java.util.Objects;

import com.cplerings.core.common.pagination.Buildable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDataResponse<T> extends AbstractResponse {

    private T data;

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public abstract static class AbstractDataResponseBuilder<S extends AbstractDataResponseBuilder<S, R, T>, R extends AbstractDataResponse<T>, T>
            extends AbstractResponseBuilder<S, R> implements Buildable<R> {

        private final Type type = Type.DATA;

        private T data;

        public final S data(T data) {
            Objects.requireNonNull(data, "Data must not be null");
            this.data = data;
            return self();
        }
    }
}
