package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;

public class ViewCustomOrderResponse extends AbstractDataResponse<CustomOrderData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, ViewCustomOrderResponse, CustomOrderData> {

        @Override
        protected ViewCustomOrderResponse getResponseInstance() {
            return new ViewCustomOrderResponse();
        }
    }
}
