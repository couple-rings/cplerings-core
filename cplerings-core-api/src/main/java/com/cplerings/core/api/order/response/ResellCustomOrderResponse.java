package com.cplerings.core.api.order.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.order.AResellOrder;

public class ResellCustomOrderResponse extends AbstractDataResponse<AResellOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, ResellCustomOrderResponse, AResellOrder> {

        @Override
        protected ResellCustomOrderResponse getResponseInstance() {
            return new ResellCustomOrderResponse();
        }
    }
}
