package com.cplerings.core.application.dashboard.output;

import com.cplerings.core.application.dashboard.datasource.data.PaymentOrder;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewPaymentWithDateOutput extends AbstractPaginatedOutput<PaymentOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewPaymentWithDateOutput, PaymentOrder> {

        @Override
        protected ViewPaymentWithDateOutput getOutputInstance() {
            return new ViewPaymentWithDateOutput();
        }
    }
}
