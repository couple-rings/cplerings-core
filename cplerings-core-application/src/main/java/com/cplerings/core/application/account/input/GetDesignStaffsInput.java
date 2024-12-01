package com.cplerings.core.application.account.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetDesignStaffsInput extends AbstractPaginatedInput {

    private Long branchId;

    public static Builder builder() {
        return new Builder();
    }

    @Getter
    @NoArgsConstructor
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, GetDesignStaffsInput> {

        private Long branchId;

        public Builder branchId(Long branchId) {
            this.branchId = branchId;
            return self();
        }

        @Override
        public GetDesignStaffsInput build() {
            final GetDesignStaffsInput input = super.build();
            input.setBranchId(branchId);
            return input;
        }

        @Override
        protected GetDesignStaffsInput getRequestInstance() {
            return new GetDesignStaffsInput();
        }
    }
}
