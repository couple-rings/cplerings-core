package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewTotalTypeOfPaymentData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTotalTypeOfPaymentResponse extends AbstractDataResponse<ViewTotalTypeOfPaymentData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewTotalTypeOfPaymentResponse, ViewTotalTypeOfPaymentData> {

        @Override
        protected ViewTotalTypeOfPaymentResponse getResponseInstance() {
            return new ViewTotalTypeOfPaymentResponse();
        }
    }
}
