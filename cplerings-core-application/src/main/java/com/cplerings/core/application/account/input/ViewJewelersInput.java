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
public class ViewJewelersInput extends AbstractPaginatedInput {

    private Long branchId;

    public static Builder builder() {
        return new Builder();
    }

    @Getter
    @NoArgsConstructor
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewJewelersInput> {

        private Long branchId;

        public Builder branchId(Long branchId) {
            this.branchId = branchId;
            return self();
        }

        @Override
        public ViewJewelersInput build() {
            final ViewJewelersInput input = super.build();
            input.setBranchId(branchId);
            return input;
        }

        @Override
        protected ViewJewelersInput getRequestInstance() {
            return new ViewJewelersInput();
        }
    }
}
