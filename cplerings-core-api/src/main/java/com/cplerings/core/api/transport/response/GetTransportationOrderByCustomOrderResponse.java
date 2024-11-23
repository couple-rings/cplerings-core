package com.cplerings.core.api.transport.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.order.ATransportationOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetTransportationOrderByCustomOrderResponse extends AbstractDataResponse<ATransportationOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, GetTransportationOrderByCustomOrderResponse, ATransportationOrder> {

        @Override
        protected GetTransportationOrderByCustomOrderResponse getResponseInstance() {
            return new GetTransportationOrderByCustomOrderResponse();
        }
    }
}
