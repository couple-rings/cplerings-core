package com.cplerings.core.test.unit.pagination;

import com.cplerings.core.application.shared.pagination.AbstractPaginationOutput;

class DummyPaginationOutput extends AbstractPaginationOutput<String> {

    static Builder builder() {
        return new Builder();
    }

    static final class Builder extends AbstractBuilder<Builder, DummyPaginationOutput, String> {

        @Override
        public DummyPaginationOutput build() {
            return populatePaginationInformation(new DummyPaginationOutput());
        }
    }
}
