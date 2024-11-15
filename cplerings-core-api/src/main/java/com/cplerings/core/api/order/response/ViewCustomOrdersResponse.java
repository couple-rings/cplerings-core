package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.CustomOrdersData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

public class ViewCustomOrdersResponse extends AbstractPaginatedResponse<CustomOrdersData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewCustomOrdersResponse, CustomOrdersData> {

        @Override
        protected ViewCustomOrdersResponse getResponseInstance() {
            return new ViewCustomOrdersResponse();
        }
    }
}
