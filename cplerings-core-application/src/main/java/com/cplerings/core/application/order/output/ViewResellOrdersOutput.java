package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewResellOrdersOutput extends AbstractPaginatedOutput<AResellOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewResellOrdersOutput, AResellOrder> {

        @Override
        protected ViewResellOrdersOutput getOutputInstance() {
            return new ViewResellOrdersOutput();
        }
    }
}
