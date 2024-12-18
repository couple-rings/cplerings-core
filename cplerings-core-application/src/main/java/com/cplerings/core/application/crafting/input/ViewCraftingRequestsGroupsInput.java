package com.cplerings.core.application.crafting.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCraftingRequestsGroupsInput extends AbstractPaginatedInput {

    private Long branchId;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCraftingRequestsGroupsInput> {

        private Long branchId;

        public Builder branchId(Long branchId) {
            this.branchId = branchId;
            return self();
        }


        @Override
        public ViewCraftingRequestsGroupsInput build() {
            final ViewCraftingRequestsGroupsInput input = super.build();
            input.setBranchId(branchId);
            return input;
        }

        @Override
        protected ViewCraftingRequestsGroupsInput getRequestInstance() {
            return new ViewCraftingRequestsGroupsInput();
        }
    }
}
