package com.cplerings.core.application.dashboard.output;

import com.cplerings.core.application.shared.entity.order.ARefund;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewRefundOrdersWithDateOutput extends AbstractPaginatedOutput<ARefund> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewRefundOrdersWithDateOutput, ARefund> {

        @Override
        protected ViewRefundOrdersWithDateOutput getOutputInstance() {
            return new ViewRefundOrdersWithDateOutput();
        }
    }
}
