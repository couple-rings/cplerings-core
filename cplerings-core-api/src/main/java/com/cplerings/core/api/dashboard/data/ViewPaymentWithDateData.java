package com.cplerings.core.api.dashboard.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.dashboard.datasource.data.PaymentOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewPaymentWithDateData extends AbstractPaginatedData<PaymentOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedData.AbstractPaginatedDataBuilder<Builder, ViewPaymentWithDateData, PaymentOrder> {

        @Override
        protected ViewPaymentWithDateData getDataInstance() {
            return new ViewPaymentWithDateData();
        }
    }
}
