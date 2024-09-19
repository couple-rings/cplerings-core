package com.cplerings.core.test.unit.pagination;

import com.cplerings.core.application.shared.pagination.AbstractPaginationOutput;

class DummyPaginationOutput extends AbstractPaginationOutput<String> {

    static class Builder extends AbstractBuilder<Builder, DummyPaginationOutput, String> {

        @Override
        public DummyPaginationOutput build() {
            calculatePagination();
            return populatePaginationInformation(new DummyPaginationOutput());
        }
    }

    static Builder builder() {
        return new Builder();
    }
}
