package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.order.ACustomOrder;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomOrdersOutput extends AbstractPaginatedOutput<ACustomOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCustomOrdersOutput, ACustomOrder> {

        @Override
        protected ViewCustomOrdersOutput getOutputInstance() {
            return new ViewCustomOrdersOutput();
        }
    }
}
