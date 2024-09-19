package com.cplerings.core.api.shared;

import com.cplerings.core.common.pagination.Buildable;
import com.cplerings.core.common.pagination.Pageable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@Builder
public abstract class AbstractPaginatedResponse<T> extends AbstractResponse {

    private int page;
    private int pageSize;
    private int totalPages;
    private int count;
    private Collection<T> data;

    @Getter
    public abstract static class AbstractBuilder<S extends AbstractBuilder<S, R, T>,
            R extends AbstractPaginatedResponse<T>, T> implements Buildable<R> {

        private final Type type = Type.PAGINATED_DATA;

        private int page;
        private int pageSize;
        private int totalPages;
        private int count;
        private Collection<T> data;

        @SuppressWarnings("unchecked")
        public S self() {
            return (S) this;
        }

        public S populatePaginationInfo(Pageable pageable) {
            Objects.requireNonNull(pageable, "Pageable implementation must not be null");
            this.page = pageable.getPage();
            this.pageSize = pageable.getPageSize();
            this.totalPages = pageable.getTotalPages();
            return self();
        }

        public S data(Collection<T> data) {
            this.data = Objects.requireNonNull(data, "Data must not be null");
            this.count = data.size();
            return self();
        }
    }
}
