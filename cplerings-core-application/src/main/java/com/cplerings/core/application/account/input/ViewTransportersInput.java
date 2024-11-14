package com.cplerings.core.application.account.input;

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
public class ViewTransportersInput extends AbstractPaginatedInput {

    private Long branchId;

    public static Builder builder() {
        return new Builder();
    }

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewTransportersInput> {

        private Long branchId;

        public Builder branchId(Long branchId) {
            this.branchId = branchId;
            return self();
        }

        @Override
        public ViewTransportersInput build() {
            final ViewTransportersInput input = super.build();
            input.setBranchId(branchId);
            return input;
        }

        @Override
        protected ViewTransportersInput getRequestInstance() {
            return new ViewTransportersInput();
        }
    }
}
