package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.PaymentInfosData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomOrderPaymentsResponse extends AbstractDataResponse<PaymentInfosData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, ViewCustomOrderPaymentsResponse, PaymentInfosData> {

        @Override
        protected ViewCustomOrderPaymentsResponse getResponseInstance() {
            return new ViewCustomOrderPaymentsResponse();
        }
    }
}
