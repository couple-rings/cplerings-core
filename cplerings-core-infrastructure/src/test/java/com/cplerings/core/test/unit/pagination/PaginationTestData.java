package com.cplerings.core.test.unit.pagination;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Getter(AccessLevel.PACKAGE)
@Builder
@ToString
final class PaginationTestData {

    private int page;
    private int pageSize;
    private int totalCount;
    private Collection<String> data;
    private int expectedTotalPages;
}
