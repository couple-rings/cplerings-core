package com.cplerings.core.api.dashboard.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.payment.APaymentPayment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewPaymentWithDateData extends AbstractPaginatedData<APaymentPayment> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedData.AbstractPaginatedDataBuilder<Builder, ViewPaymentWithDateData, APaymentPayment> {

        @Override
        protected ViewPaymentWithDateData getDataInstance() {
            return new ViewPaymentWithDateData();
        }
    }
}
