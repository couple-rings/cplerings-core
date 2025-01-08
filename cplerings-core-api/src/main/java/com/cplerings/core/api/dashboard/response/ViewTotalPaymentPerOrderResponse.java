package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewTotalPaymentPerOrderData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTotalPaymentPerOrderResponse extends AbstractDataResponse<ViewTotalPaymentPerOrderData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewTotalPaymentPerOrderResponse, ViewTotalPaymentPerOrderData> {

        @Override
        protected ViewTotalPaymentPerOrderResponse getResponseInstance() {
            return new ViewTotalPaymentPerOrderResponse();
        }
    }
}
