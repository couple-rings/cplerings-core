package com.cplerings.core.api.transport.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.api.transport.data.TransportationOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateImageDeliveryResponse extends AbstractDataResponse<TransportationOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, UpdateImageDeliveryResponse, TransportationOrder> {

        @Override
        protected UpdateImageDeliveryResponse getResponseInstance() {
            return new UpdateImageDeliveryResponse();
        }
    }
}
