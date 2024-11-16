package com.cplerings.core.application.crafting.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCraftingRequestsGroupsInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCraftingRequestsGroupsInput> {

        @Override
        protected ViewCraftingRequestsGroupsInput getRequestInstance() {
            return new ViewCraftingRequestsGroupsInput();
        }
    }
}
