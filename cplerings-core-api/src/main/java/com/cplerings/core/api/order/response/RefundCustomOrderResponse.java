package com.cplerings.core.api.order.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.order.ARefund;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefundCustomOrderResponse extends AbstractDataResponse<ARefund> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, RefundCustomOrderResponse, ARefund> {

        @Override
        protected RefundCustomOrderResponse getResponseInstance() {
            return new RefundCustomOrderResponse();
        }
    }
}
