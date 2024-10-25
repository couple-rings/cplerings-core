package com.cplerings.core.application.shared.pagination;

import com.cplerings.core.common.builder.Buildable;
import com.cplerings.core.common.fluentapi.AbstractSelf;
import com.cplerings.core.common.pagination.PaginationConstant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractPaginatedInput {

    private int page = PaginationConstant.DEFAULT_PAGE;
    private int pageSize = PaginationConstant.DEFAULT_PAGE_SIZE;

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static abstract class AbstractPaginatedInputBuilder<S extends AbstractPaginatedInputBuilder<S, INPUT>, INPUT extends AbstractPaginatedInput>
            extends AbstractSelf<S> implements Buildable<INPUT> {

        private int page;
        private int pageSize;

        public S page(int page) {
            this.page = (page < 0) ? PaginationConstant.DEFAULT_PAGE : page;
            return self();
        }

        public S pageSize(int pageSize) {
            this.pageSize = (pageSize <= 0) ? PaginationConstant.DEFAULT_PAGE_SIZE : pageSize;
            return self();
        }

        @Override
        public INPUT build() {
            final INPUT input = getRequestInstance();
            input.setPage(page);
            input.setPageSize(pageSize);
            return input;
        }

        protected abstract INPUT getRequestInstance();
    }
}
