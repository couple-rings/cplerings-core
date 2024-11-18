package com.cplerings.core.application.order.input;

import com.cplerings.core.application.shared.entity.order.ACustomOrderStatus;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomOrdersInput extends AbstractPaginatedInput {

    private Long customerId;
    private Long jewelerId;
    private ACustomOrderStatus status;
    private Long branchId;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCustomOrdersInput> {

        private Long customerId;
        private Long jewelerId;
        private ACustomOrderStatus status;
        private Long branchId;

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return self();
        }

        public Builder jewelerId(Long jewelerId) {
            this.jewelerId = jewelerId;
            return self();
        }

        public Builder status(ACustomOrderStatus status) {
            this.status = status;
            return self();
        }

        public Builder branchId(Long branchId) {
            this.branchId = branchId;
            return self();
        }

        @Override
        public ViewCustomOrdersInput build() {
            final ViewCustomOrdersInput input = super.build();
            input.setCustomerId(customerId);
            input.setJewelerId(jewelerId);
            input.setStatus(status);
            input.setBranchId(branchId);
            return input;
        }

        @Override
        protected ViewCustomOrdersInput getRequestInstance() {
            return new ViewCustomOrdersInput();
        }
    }
}
