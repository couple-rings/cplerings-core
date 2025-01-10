package com.cplerings.core.api.order.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.order.ARefund;

public class ViewRefundResponse extends AbstractDataResponse<ARefund> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponse.AbstractDataResponseBuilder<Builder, ViewRefundResponse, ARefund> {

        @Override
        protected ViewRefundResponse getResponseInstance() {
            return new ViewRefundResponse();
        }
    }
}
