package com.cplerings.core.application.order.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewRefundOrdersInput extends AbstractPaginatedInput {

    private Long staffId;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewRefundOrdersInput> {

        private Long staffId;

        public Builder staffId(Long staffId) {
            this.staffId = staffId;
            return self();
        }

        @Override
        public ViewRefundOrdersInput build() {
            final ViewRefundOrdersInput input = super.build();
            input.setStaffId(staffId);
            return input;
        }

        @Override
        protected ViewRefundOrdersInput getRequestInstance() {
            return new ViewRefundOrdersInput();
        }
    }
}
