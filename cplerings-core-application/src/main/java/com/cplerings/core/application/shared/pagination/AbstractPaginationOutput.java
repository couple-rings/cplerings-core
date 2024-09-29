package com.cplerings.core.application.shared.pagination;

import com.cplerings.core.common.fluentapi.AbstractSelf;
import com.cplerings.core.common.pagination.Buildable;
import com.cplerings.core.common.pagination.Pageable;
import com.cplerings.core.common.pagination.PaginationConstant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class AbstractPaginationOutput<T> implements Pageable {

    protected int page;
    protected int pageSize = PaginationConstant.DEFAULT_PAGE_SIZE;
    protected int count;
    protected int totalPages;
    protected Collection<T> data;

    public static abstract class AbstractBuilder<S extends AbstractBuilder<S, P, T>, P extends AbstractPaginationOutput<T>, T>
            extends AbstractSelf<S>
            implements Buildable<P> {

        @Getter(AccessLevel.PROTECTED)
        private int page = PaginationConstant.DEFAULT_PAGE;

        @Getter(AccessLevel.PROTECTED)
        private int pageSize = PaginationConstant.DEFAULT_PAGE_SIZE;

        private int totalCount;

        @Getter(AccessLevel.PROTECTED)
        private int totalPages;

        @Getter(AccessLevel.PROTECTED)
        private Collection<T> data;

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

        public final S totalCount(int totalCount) {
            if (totalCount < 0) {
                throw new IllegalArgumentException("Total count cannot be negative");
            }
            this.totalCount = totalCount;
            return self();
        }

        public final S data(Collection<T> data) {
            Objects.requireNonNull(data, "Data cannot be null");
            this.data = data;
            return self();
        }

        protected final P populatePaginationInformation(P output) {
            Objects.requireNonNull(output, "Output is required");
            calculatePagination();
            output.setPage(page);
            output.setPageSize(pageSize);
            output.setCount(data.size());
            output.setTotalPages(totalPages);
            output.setData(data);
            return output;
        }

        private void calculatePagination() {
            Objects.requireNonNull(data, "Data is required");
            this.totalPages = Math.ceilDiv(totalCount, pageSize);
        }
    }
}
