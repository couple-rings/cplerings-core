package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.ACustomDesign;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewCustomDesignsOutput extends AbstractPaginatedOutput<ACustomDesign> {

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCustomDesignsOutput, ACustomDesign> {

        @Override
        protected ViewCustomDesignsOutput getOutputInstance() {
            return new ViewCustomDesignsOutput();
        }
    }
}
