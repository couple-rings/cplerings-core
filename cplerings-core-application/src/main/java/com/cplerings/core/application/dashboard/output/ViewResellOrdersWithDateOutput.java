package com.cplerings.core.application.dashboard.output;

import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewResellOrdersWithDateOutput extends AbstractPaginatedOutput<AResellOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewResellOrdersWithDateOutput, AResellOrder> {

        @Override
        protected ViewResellOrdersWithDateOutput getOutputInstance() {
            return new ViewResellOrdersWithDateOutput();
        }
    }
}
