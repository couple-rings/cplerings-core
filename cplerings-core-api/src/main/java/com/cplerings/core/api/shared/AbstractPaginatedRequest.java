package com.cplerings.core.api.shared;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public abstract class AbstractPaginatedRequest {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Min(value = 0, message = "page must be larger or equal 0")
    private int pageNumber;

    @Min(value = 1, message = "The page size must be larger or equal 1")
    private int pageSize;

    public void setPageNumber() {
        this.pageNumber = pageNumber < 0 ? DEFAULT_PAGE : pageNumber;
    }

    public void setPageSize() {
        this.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }

}
