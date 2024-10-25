package com.cplerings.core.test.unit.pagination;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
class DummyPaginatedOutput extends AbstractPaginatedOutput<String> {

    static Builder builder() {
        return new Builder();
    }

    static final class Builder extends AbstractPaginatedOutputBuilder<Builder, DummyPaginatedOutput, String> {

        @Override
        protected DummyPaginatedOutput getOutputInstance() {
            return new DummyPaginatedOutput();
        }
    }
}
