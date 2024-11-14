package com.cplerings.core.application.transport.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewTransportationOrdersInput extends AbstractPaginatedInput {

    private Long transporterId;

    public static Builder builder() {
        return new Builder();
    }

    @Getter
    @NoArgsConstructor
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewTransportationOrdersInput> {

        private Long transporterId;

        public Builder transporterId(Long transporterId) {
            this.transporterId = transporterId;
            return self();
        }

        @Override
        public ViewTransportationOrdersInput build() {
            final ViewTransportationOrdersInput input = super.build();
            input.setTransporterId(transporterId);
            return input;
        }

        @Override
        protected ViewTransportationOrdersInput getRequestInstance() {
            return new ViewTransportationOrdersInput();
        }
    }
}
