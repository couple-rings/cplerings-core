package com.cplerings.core.application.dashboard.output;

import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomOrdersWithDateOutput extends AbstractPaginatedOutput<CombinedOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCustomOrdersWithDateOutput, CombinedOrder> {

        @Override
        protected ViewCustomOrdersWithDateOutput getOutputInstance() {
            return new ViewCustomOrdersWithDateOutput();
        }
    }
}
