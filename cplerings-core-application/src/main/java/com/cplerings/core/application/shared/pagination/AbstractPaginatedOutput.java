package com.cplerings.core.application.shared.pagination;

import com.cplerings.core.common.builder.Buildable;
import com.cplerings.core.common.fluentapi.AbstractSelf;
import com.cplerings.core.common.pagination.Pageable;
import com.cplerings.core.common.pagination.PaginationConstant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PUBLIC)
public abstract class AbstractPaginatedOutput<T> implements Pageable {

    protected int page;
    protected int pageSize = PaginationConstant.DEFAULT_PAGE_SIZE;
    protected long count;
    protected int totalPages;
    protected Collection<T> items;

    public static abstract class AbstractPaginatedOutputBuilder<S extends AbstractPaginatedOutputBuilder<S, P, T>, P extends AbstractPaginatedOutput<T>, T>
            extends AbstractSelf<S> implements Buildable<P> {

        @Getter(AccessLevel.PUBLIC)
        private int page = PaginationConstant.DEFAULT_PAGE;

        @Getter(AccessLevel.PUBLIC)
        private int pageSize = PaginationConstant.DEFAULT_PAGE_SIZE;

        @Getter(AccessLevel.PUBLIC)
        private long count;

        @Getter(AccessLevel.PUBLIC)
        private int totalPages;

        @Getter(AccessLevel.PUBLIC)
        private Collection<T> items;

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

        public final S items(Collection<T> items) {
            Objects.requireNonNull(items, "Data cannot be null");
            this.items = items;
            return self();
        }

        @Override
        public P build() {
            final P output = getOutputInstance();
            calculatePagination();
            output.setPage(page);
            output.setPageSize(pageSize);
            output.setCount(items.size());
            calculatePagination();
            output.setTotalPages(totalPages);
            output.setItems(items);
            return output;
        }

        protected abstract P getOutputInstance();

        private void calculatePagination() {
            this.totalPages = Math.ceilDiv((int) count, pageSize);
        }
    }
}
