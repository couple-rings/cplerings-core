package com.cplerings.core.application.dashboard.input;

import java.time.Instant;

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
public class ViewCustomOrdersWithDateInput extends AbstractPaginatedInput {

    private Instant startDate;
    private Instant endDate;

    public static Builder builder() {
        return new Builder();
    }

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCustomOrdersWithDateInput> {

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
        public ViewCustomOrdersWithDateInput build() {
            final ViewCustomOrdersWithDateInput input = super.build();
            input.setStartDate(startDate);
            input.setEndDate(endDate);
            return input;
        }

        @Override
        protected ViewCustomOrdersWithDateInput getRequestInstance() {
            return new ViewCustomOrdersWithDateInput();
        }
    }
}
