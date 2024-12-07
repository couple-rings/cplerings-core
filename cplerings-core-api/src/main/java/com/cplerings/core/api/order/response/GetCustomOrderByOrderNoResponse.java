package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetCustomOrderByOrderNoResponse extends AbstractDataResponse<CustomOrderData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, GetCustomOrderByOrderNoResponse, CustomOrderData> {

        @Override
        protected GetCustomOrderByOrderNoResponse getResponseInstance() {
            return new GetCustomOrderByOrderNoResponse();
        }
    }
}
