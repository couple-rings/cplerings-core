package com.cplerings.core.common.pagination;

public interface Pageable {

    int getPage();

    int getPageSize();

    int getTotalPages();

    long getCount();
}
