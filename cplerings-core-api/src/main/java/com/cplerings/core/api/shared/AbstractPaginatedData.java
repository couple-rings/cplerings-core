package com.cplerings.core.api.shared;

import com.cplerings.core.common.builder.Buildable;
import com.cplerings.core.common.fluentapi.AbstractSelf;
import com.cplerings.core.common.pagination.Pageable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
public abstract class AbstractPaginatedData<T> implements Pageable {

    private int page;
    private int pageSize;
    private int totalPages;
    private int count;
    private Collection<T> items;

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public abstract static class AbstractPaginatedDataBuilder<S extends AbstractPaginatedDataBuilder<S, D, T>, D extends AbstractPaginatedData<T>, T>
            extends AbstractSelf<S> implements Buildable<D> {

        private int page;
        private int pageSize;
        private int totalPages;
        private int count;
        private Collection<T> items;

        public final S items(Collection<T> items) {
            Objects.requireNonNull(items, "Data must not be null");
            this.count = items.size();
            this.items = items;
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

        @Override
        public D build() {
            final D dataInstance = getDataInstance();
            calculatePagination();
            dataInstance.setPage(page);
            dataInstance.setPageSize(pageSize);
            dataInstance.setTotalPages(totalPages);
            dataInstance.setCount(count);
            dataInstance.setItems(items);
            return dataInstance;
        }

        protected abstract D getDataInstance();

        private void calculatePagination() {
            Objects.requireNonNull(items, "Data is required");
            this.totalPages = Math.ceilDiv(count, pageSize);
        }
    }
}
