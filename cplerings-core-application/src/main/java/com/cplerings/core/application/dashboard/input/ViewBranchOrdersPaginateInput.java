package com.cplerings.core.application.dashboard.input;

import java.time.Instant;

import com.cplerings.core.application.dashboard.datasource.data.OrderTypeStatistic;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewBranchOrdersPaginateInput extends AbstractPaginatedInput {

    private Instant startDate;
    private Instant endDate;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewBranchOrdersPaginateInput> {

        private Instant startDate;
        private Instant endDate;

        public Builder startDate(Instant startDate) {
            this.startDate = startDate;
            return self();
        }

        public Builder endDate(Instant endDate) {
            this.endDate = endDate;
            return self();
        }

        @Override
        public ViewBranchOrdersPaginateInput build() {
            final ViewBranchOrdersPaginateInput input = super.build();
            input.setStartDate(startDate);
            input.setEndDate(endDate);
            return input;
        }

        @Override
        protected ViewBranchOrdersPaginateInput getRequestInstance() {
            return new ViewBranchOrdersPaginateInput();
        }
    }
}
