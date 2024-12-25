package com.cplerings.core.api.order.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.order.AResellOrder;

public class ViewResellOrderResponse extends AbstractDataResponse<AResellOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, ViewResellOrderResponse, AResellOrder> {

        @Override
        protected ViewResellOrderResponse getResponseInstance() {
            return new ViewResellOrderResponse();
        }
    }
}
