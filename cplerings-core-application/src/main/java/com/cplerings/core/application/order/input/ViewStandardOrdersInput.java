package com.cplerings.core.application.order.input;

import com.cplerings.core.application.shared.entity.order.AStandardOrderStatus;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewStandardOrdersInput extends AbstractPaginatedInput {

    private Long branchId;
    private AStandardOrderStatus status;
    private Long customerId;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewStandardOrdersInput> {

        private Long branchId;
        private AStandardOrderStatus status;
        private Long customerId;

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return self();
        }

        public Builder branchId(Long branchId) {
            this.branchId = branchId;
            return self();
        }

        public Builder status(AStandardOrderStatus status) {
            this.status = status;
            return self();
        }

        @Override
        public ViewStandardOrdersInput build() {
            final ViewStandardOrdersInput input = super.build();
            input.setCustomerId(customerId);
            input.setBranchId(branchId);
            input.setStatus(status);
            return input;
        }

        @Override
        protected ViewStandardOrdersInput getRequestInstance() {
            return new ViewStandardOrdersInput();
        }
    }
}
