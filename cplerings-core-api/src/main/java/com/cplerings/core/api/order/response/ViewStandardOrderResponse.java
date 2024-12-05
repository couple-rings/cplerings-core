package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.shared.AbstractDataResponse;

public class ViewStandardOrderResponse extends AbstractDataResponse<StandardOrderData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, ViewStandardOrderResponse, StandardOrderData> {

        @Override
        protected ViewStandardOrderResponse getResponseInstance() {
            return new ViewStandardOrderResponse();
        }
    }
}
