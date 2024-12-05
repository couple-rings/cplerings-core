package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.StandardOrderPaymentLinkData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PayStandardOrderResponse extends AbstractDataResponse<StandardOrderPaymentLinkData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, PayStandardOrderResponse, StandardOrderPaymentLinkData> {

        @Override
        protected PayStandardOrderResponse getResponseInstance() {
            return new PayStandardOrderResponse();
        }
    }
}
