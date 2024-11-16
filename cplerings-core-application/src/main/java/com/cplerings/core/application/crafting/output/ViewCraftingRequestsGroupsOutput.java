package com.cplerings.core.application.crafting.output;

import com.cplerings.core.application.shared.entity.account.AAccountCraftingRequest;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCraftingRequestsGroupsOutput extends AbstractPaginatedOutput<AAccountCraftingRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCraftingRequestsGroupsOutput, AAccountCraftingRequest> {

        @Override
        protected ViewCraftingRequestsGroupsOutput getOutputInstance() {
            return new ViewCraftingRequestsGroupsOutput();
        }
    }
}
