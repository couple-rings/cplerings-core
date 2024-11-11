package com.cplerings.core.application.crafting.output;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequest;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCraftingRequestsOutput extends AbstractPaginatedOutput<ACraftingRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCraftingRequestsOutput, ACraftingRequest> {

        @Override
        protected ViewCraftingRequestsOutput getOutputInstance() {
            return new ViewCraftingRequestsOutput();
        }
    }
}
