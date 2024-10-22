package com.cplerings.core.api.shared;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractPaginatedRequest<T extends AbstractPaginatedRequest<T, B>, B extends AbstractPaginatedRequest.AbstractBuilder<T, B>> {

    protected static final int DEFAULT_PAGE = 0;
    protected static final int DEFAULT_PAGE_SIZE = 10;

    @Min(value = 1, message = "Page must be larger or equal to 0")
    protected int page;

    @Min(value = 1, message = "The page size must be larger or equal to 1")
    protected int pageSize;

    public static abstract class AbstractBuilder<T extends AbstractPaginatedRequest<T, B>, B extends AbstractBuilder<T, B>> {
        protected T instance;

        protected AbstractBuilder(T instance) {
            this.instance = instance;
        }

        public B page(int page) {
            instance.page = page < 0 ? DEFAULT_PAGE : page;
            return self();
        }

        public B pageSize(int pageSize) {
            instance.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
            return self();
        }

        protected abstract B self();

        public T build() {
            return instance;
        }
    }
}
