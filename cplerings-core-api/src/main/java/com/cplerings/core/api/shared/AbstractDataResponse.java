package com.cplerings.core.api.shared;

import com.cplerings.core.common.pagination.Buildable;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class AbstractDataResponse<T> extends AbstractResponse {

    private T data;

    @Getter
    public abstract static class AbstractBuilder<S extends AbstractBuilder<S, R, T>,
            R extends AbstractDataResponse<T>, T> implements Buildable<R> {

        private final Type type = Type.DATA;

        private T data;

        @SuppressWarnings("unchecked")
        public S self() {
            return (S) this;
        }

        public S data(T data) {
            this.data = Objects.requireNonNull(data, "Data must not be null");
            return self();
        }
    }
}
