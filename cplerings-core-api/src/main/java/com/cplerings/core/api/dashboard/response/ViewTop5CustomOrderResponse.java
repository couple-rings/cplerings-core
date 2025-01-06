package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewTop5CustomOrderData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewTop5CustomOrderResponse extends AbstractDataResponse<ViewTop5CustomOrderData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewTop5CustomOrderResponse, ViewTop5CustomOrderData> {

        @Override
        protected ViewTop5CustomOrderResponse getResponseInstance() {
            return new ViewTop5CustomOrderResponse();
        }
    }
}
