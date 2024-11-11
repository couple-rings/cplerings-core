package com.cplerings.core.application.design.output;

import com.cplerings.core.application.crafting.output.ViewCraftingRequestsOutput;
import com.cplerings.core.application.shared.entity.design.ACustomDesign;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomDesignsOutput extends AbstractPaginatedOutput<ACustomDesign> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCustomDesignsOutput, ACustomDesign> {

        @Override
        protected ViewCustomDesignsOutput getOutputInstance() {
            return new ViewCustomDesignsOutput();
        }
    }
}
