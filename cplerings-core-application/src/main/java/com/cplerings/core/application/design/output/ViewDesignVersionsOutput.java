package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.ADesignVersion;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

public class ViewDesignVersionsOutput extends AbstractPaginatedOutput<ADesignVersion>{

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewDesignVersionsOutput, ADesignVersion> {

        @Override
        protected ViewDesignVersionsOutput getOutputInstance() {
            return new ViewDesignVersionsOutput();
        }
    }
}
