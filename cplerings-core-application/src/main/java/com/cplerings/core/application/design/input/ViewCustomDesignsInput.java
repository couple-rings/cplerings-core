package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.entity.shared.AState;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewCustomDesignsInput extends AbstractPaginatedInput {

    private AState state;
    private Long customerId;

    public static Builder builder() {
        return new Builder();
    }

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCustomDesignsInput> {

        private AState state;
        private Long customerId;

        public Builder status(AState state) {
            this.state = state;
            return self();
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return self();
        }

        @Override
        public ViewCustomDesignsInput build() {
            final ViewCustomDesignsInput input = super.build();
            input.setCustomerId(customerId);
            input.setState(state);
            return input;
        }

        @Override
        protected ViewCustomDesignsInput getRequestInstance() {
            return new ViewCustomDesignsInput();
        }
    }
}
