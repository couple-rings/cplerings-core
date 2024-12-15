package com.cplerings.core.application.order.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewResellOrdersInput extends AbstractPaginatedInput {

    private Long staffId;
    private Long customerId;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewResellOrdersInput> {

        private Long staffId;
        private Long customerId;

        public Builder staffId(Long staffId) {
            this.staffId = staffId;
            return self();
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return self();
        }

        @Override
        public ViewResellOrdersInput build() {
            final ViewResellOrdersInput input = super.build();
            input.setStaffId(staffId);
            input.setCustomerId(customerId);
            return input;
        }

        @Override
        protected ViewResellOrdersInput getRequestInstance() {
            return new ViewResellOrdersInput();
        }
    }
}
