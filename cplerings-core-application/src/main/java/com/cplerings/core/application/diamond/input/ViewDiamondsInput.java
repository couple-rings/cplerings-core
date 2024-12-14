package com.cplerings.core.application.diamond.input;

import com.cplerings.core.application.shared.entity.shared.AState;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDiamondsInput extends AbstractPaginatedInput {

    private Long branchId;
    private String giaReportNumber;
    private AState state;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewDiamondsInput> {

        private Long branchId;
        private String giaReportNumber;
        private AState state;

        public Builder giaReportNumber(String giaReportNumber) {
            this.giaReportNumber = giaReportNumber;
            return self();
        }

        public Builder branchId(Long branchId) {
            this.branchId = branchId;
            return self();
        }

        public Builder state(AState state) {
            this.state = state;
            return self();
        }

        @Override
        public ViewDiamondsInput build() {
            final ViewDiamondsInput input = super.build();
            input.setGiaReportNumber(giaReportNumber);
            input.setBranchId(branchId);
            return input;
        }

        @Override
        protected ViewDiamondsInput getRequestInstance() {
            return new ViewDiamondsInput();
        }
    }
}
