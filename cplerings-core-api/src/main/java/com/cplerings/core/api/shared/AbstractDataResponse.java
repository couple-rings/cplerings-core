package com.cplerings.core.api.shared;

import com.cplerings.core.common.pagination.Buildable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

        public R build() {
            final R response = getResponseInstance();
            response.setData(data);
            response.setType(type);
            return response;
        }

        protected abstract R getResponseInstance();
    }
}
