package com.cplerings.core.application.dashboard.output;

import com.cplerings.core.application.shared.entity.payment.APaymentPayment;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewPaymentWithDateOutput extends AbstractPaginatedOutput<APaymentPayment> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewPaymentWithDateOutput, APaymentPayment> {

        @Override
        protected ViewPaymentWithDateOutput getOutputInstance() {
            return new ViewPaymentWithDateOutput();
        }
    }
}
