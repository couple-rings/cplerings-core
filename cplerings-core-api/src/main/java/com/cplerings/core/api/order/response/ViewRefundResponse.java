package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.RefundData;
import com.cplerings.core.api.shared.AbstractDataResponse;

public class ViewRefundResponse extends AbstractDataResponse<RefundData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponse.AbstractDataResponseBuilder<Builder, ViewRefundResponse, RefundData> {

        @Override
        protected ViewRefundResponse getResponseInstance() {
            return new ViewRefundResponse();
        }
    }
}
