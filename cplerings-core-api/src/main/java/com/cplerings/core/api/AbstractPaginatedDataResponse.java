package com.cplerings.core.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public abstract class AbstractPaginatedDataResponse<T> extends AbstractResponse {

    private int page;
    private int pageSize;
    private int totalPages;
    private int count;
    private Collection<T> data;

    public static <T, R extends AbstractPaginatedDataResponse<T>> R create(Collection<T> data,
                                                                           APIPaginationInformation apiPaginationInformation,
                                                                           Class<R> clazz) {
        return null;
    }
}
