package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;
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
public class ViewCustomRequestsInput extends AbstractPaginatedInput {

    private ACustomRequestStatus status;
    private Long customerId;
    private Long staffId;

    public static Builder builder() {
        return new Builder();
    }

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCustomRequestsInput> {

        private ACustomRequestStatus status;
        private Long customerId;
        private Long staffId;

        public Builder status(ACustomRequestStatus status) {
            this.status = status;
            return self();
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return self();
        }

        public Builder staffId(Long staffId) {
            this.staffId = staffId;
            return self();
        }

        @Override
        public ViewCustomRequestsInput build() {
            final ViewCustomRequestsInput input = super.build();
            input.setCustomerId(customerId);
            input.setStatus(status);
            input.setStaffId(staffId);
            return input;
        }

        @Override
        protected ViewCustomRequestsInput getRequestInstance() {
            return new ViewCustomRequestsInput();
        }
    }
}
