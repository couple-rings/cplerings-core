package com.cplerings.core.application.transport.input;

import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;
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
    private Long branchId;
    private ATransportationOrderStatus status;

    public static Builder builder() {
        return new Builder();
    }

    @Getter
    @NoArgsConstructor
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewTransportationOrdersInput> {

        private Long transporterId;
        private Long branchId;
        private ATransportationOrderStatus status;

        public Builder branchId(Long branchId) {
            this.branchId = branchId;
            return self();
        }

        public Builder status(ATransportationOrderStatus status) {
            this.status = status;
            return self();
        }

        public Builder transporterId(Long transporterId) {
            this.transporterId = transporterId;
            return self();
        }

        @Override
        public ViewTransportationOrdersInput build() {
            final ViewTransportationOrdersInput input = super.build();
            input.setTransporterId(transporterId);
            input.setBranchId(branchId);
            input.setStatus(status);
            return input;
        }

        @Override
        protected ViewTransportationOrdersInput getRequestInstance() {
            return new ViewTransportationOrdersInput();
        }
    }
}
