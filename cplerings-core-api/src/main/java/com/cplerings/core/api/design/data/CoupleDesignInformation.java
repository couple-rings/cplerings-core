package com.cplerings.core.api.design.data;

import java.util.List;

import com.cplerings.core.common.pagination.Pageable;

public record CoupleDesignInformation(List<CoupleDesign> coupleDesigns) implements Pageable {

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public int getTotalPages() {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
