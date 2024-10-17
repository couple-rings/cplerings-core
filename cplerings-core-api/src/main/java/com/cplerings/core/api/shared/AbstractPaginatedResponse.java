package com.cplerings.core.api.shared;

import com.cplerings.core.common.pagination.Buildable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
public abstract class AbstractPaginatedResponse<T> extends AbstractResponse {

    private int page;
    private int pageSize;
    private int totalPages;
    private int count;
    private Collection<T> data;

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public abstract static class AbstractPaginatedResponseBuilder<S extends AbstractPaginatedResponseBuilder<S, R, T>, R extends AbstractPaginatedResponse<T>, T>
            extends AbstractResponseBuilder<S, R> implements Buildable<R> {

        private final Type type = Type.PAGINATED_DATA;

        private int page;
        private int pageSize;
        private int totalPages;
        private int count;
        private Collection<T> data;

        public final S data(Collection<T> data) {
            Objects.requireNonNull(data, "Data must not be null");
            this.count = data.size();
            this.data = data;
            return self();
        }
    }
}
