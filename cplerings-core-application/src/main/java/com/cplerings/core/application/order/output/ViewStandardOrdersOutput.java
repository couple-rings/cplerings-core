package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.order.AStandardOrder;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewStandardOrdersOutput extends AbstractPaginatedOutput<AStandardOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewStandardOrdersOutput, AStandardOrder> {

        @Override
        protected ViewStandardOrdersOutput getOutputInstance() {
            return new ViewStandardOrdersOutput();
        }
    }
}
