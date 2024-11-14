package com.cplerings.core.application.transport.output;

import com.cplerings.core.application.shared.entity.order.ATransportationOrder;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTransportationOrdersOutput extends AbstractPaginatedOutput<ATransportationOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewTransportationOrdersOutput, ATransportationOrder> {

        @Override
        protected ViewTransportationOrdersOutput getOutputInstance() {
            return new ViewTransportationOrdersOutput();
        }
    }
}
