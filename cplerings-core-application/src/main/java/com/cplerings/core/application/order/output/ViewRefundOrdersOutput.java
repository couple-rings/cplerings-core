package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.order.ARefund;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewRefundOrdersOutput extends AbstractPaginatedOutput<ARefund> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewRefundOrdersOutput, ARefund> {

        @Override
        protected ViewRefundOrdersOutput getOutputInstance() {
            return new ViewRefundOrdersOutput();
        }
    }
}
