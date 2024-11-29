package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDesignsOutput extends AbstractPaginatedOutput<ADesign> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewDesignsOutput, ADesign> {

        @Override
        protected ViewDesignsOutput getOutputInstance() {
            return new ViewDesignsOutput();
        }
    }
}
