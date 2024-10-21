package com.cplerings.core.api.shared;

import com.cplerings.core.common.pagination.Buildable;
import com.cplerings.core.common.pagination.Pageable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
public abstract class AbstractPaginatedData<T> extends AbstractResponse
        implements Pageable {

    private int page;
    private int pageSize;
    private int totalPages;
    private int count;
    private Collection<T> data;

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public abstract static class AbstractPaginatedResponseBuilder<S extends AbstractPaginatedResponseBuilder<S, R, T>, R extends AbstractPaginatedData<T>, T>
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

        public final S page(int page) {
            if (page < 0) {
                throw new IllegalArgumentException("Page cannot be negative");
            }
            this.page = page;
            return self();
        }

        public final S pageSize(int pageSize) {
            if (pageSize <= 0) {
                throw new IllegalArgumentException("Page size must be positive");
            }
            this.pageSize = pageSize;
            return self();
        }

        public final S count(int count) {
            if (count < 0) {
                throw new IllegalArgumentException("Total count cannot be negative");
            }
            this.count = count;
            return self();
        }

        private void calculatePagination() {
            Objects.requireNonNull(data, "Data is required");
            this.totalPages = Math.ceilDiv(count, pageSize);
        }
    }
}
