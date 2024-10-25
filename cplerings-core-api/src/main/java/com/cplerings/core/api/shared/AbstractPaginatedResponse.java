package com.cplerings.core.api.shared;

import com.cplerings.core.common.builder.Buildable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class AbstractPaginatedResponse<DATA> extends AbstractResponse {

    private DATA data;

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public abstract static class AbstractPaginatedResponseBuilder<S extends AbstractPaginatedResponseBuilder<S, R, DATA>, R extends AbstractPaginatedResponse<DATA>, DATA>
            extends AbstractResponseBuilder<S, R> implements Buildable<R> {

        private final Type type = Type.PAGINATED_DATA;

        private DATA data;

        public final S data(DATA data) {
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
