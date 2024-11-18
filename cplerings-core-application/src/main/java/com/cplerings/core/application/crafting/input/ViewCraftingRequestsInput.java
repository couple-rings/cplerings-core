package com.cplerings.core.application.crafting.input;

import com.cplerings.core.application.shared.entity.crafting.ACraftingRequestStatus;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCraftingRequestsInput extends AbstractPaginatedInput {

    private Long customDesignId;
    private Long customerId;
    private ACraftingRequestStatus status;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCraftingRequestsInput> {

        private Long customDesignId;
        private Long customerId;
        private ACraftingRequestStatus status;

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return self();
        }

        public Builder status(ACraftingRequestStatus status) {
            this.status = status;
            return self();
        }

        public Builder customDesignId(Long customDesignId) {
            this.customDesignId = customDesignId;
            return self();
        }


        @Override
        public ViewCraftingRequestsInput build() {
            final ViewCraftingRequestsInput input = super.build();
            input.setCustomDesignId(customDesignId);
            input.setCustomerId(customerId);
            input.setStatus(status);
            return input;
        }

        @Override
        protected ViewCraftingRequestsInput getRequestInstance() {
            return new ViewCraftingRequestsInput();
        }
    }
}
