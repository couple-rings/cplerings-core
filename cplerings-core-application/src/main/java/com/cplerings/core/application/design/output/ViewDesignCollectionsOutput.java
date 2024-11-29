package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.ADesignCollection;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDesignCollectionsOutput extends AbstractPaginatedOutput<ADesignCollection> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewDesignCollectionsOutput, ADesignCollection> {

        @Override
        protected ViewDesignCollectionsOutput getOutputInstance() {
            return new ViewDesignCollectionsOutput();
        }
    }
}
