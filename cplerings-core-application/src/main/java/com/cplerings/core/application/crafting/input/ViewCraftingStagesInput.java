package com.cplerings.core.application.crafting.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCraftingStagesInput extends AbstractPaginatedInput {

    private Long customOrderId;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCraftingStagesInput> {

        private Long customOrderId;

        public Builder customOrderId(Long customOrderId) {
            this.customOrderId = customOrderId;
            return self();
        }

        @Override
        public ViewCraftingStagesInput build() {
            final ViewCraftingStagesInput input = super.build();
            input.setCustomOrderId(customOrderId);
            return input;
        }

        @Override
        protected ViewCraftingStagesInput getRequestInstance() {
            return new ViewCraftingStagesInput();
        }
    }
}
