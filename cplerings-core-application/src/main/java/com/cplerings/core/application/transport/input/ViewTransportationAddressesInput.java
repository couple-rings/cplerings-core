package com.cplerings.core.application.transport.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTransportationAddressesInput extends AbstractPaginatedInput {

    private Long customerId;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewTransportationAddressesInput> {

        private Long customerId;

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return self();
        }

        @Override
        public ViewTransportationAddressesInput build() {
            final ViewTransportationAddressesInput input = super.build();
            input.setCustomerId(customerId);
            return input;
        }

        @Override
        protected ViewTransportationAddressesInput getRequestInstance() {
            return new ViewTransportationAddressesInput();
        }
    }
}
