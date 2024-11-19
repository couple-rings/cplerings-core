package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssignJewelerToCustomOrderResponse extends AbstractDataResponse<CustomOrderData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, AssignJewelerToCustomOrderResponse, CustomOrderData> {

        @Override
        protected AssignJewelerToCustomOrderResponse getResponseInstance() {
            return new AssignJewelerToCustomOrderResponse();
        }
    }
}
