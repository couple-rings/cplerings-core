package com.cplerings.core.application.agreement.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewAgreementsInput extends AbstractPaginatedInput {

    private Long customerId;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewAgreementsInput> {

        private Long customerId;

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return self();
        }

        @Override
        public ViewAgreementsInput build() {
            final ViewAgreementsInput input = super.build();
            input.setCustomerId(customerId);
            return input;
        }

        @Override
        protected ViewAgreementsInput getRequestInstance() {
            return new ViewAgreementsInput();
        }
    }
}
