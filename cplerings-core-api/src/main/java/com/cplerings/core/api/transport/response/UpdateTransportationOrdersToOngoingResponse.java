package com.cplerings.core.api.transport.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.api.transport.data.TransportationOrderList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTransportationOrdersToOngoingResponse extends AbstractDataResponse<TransportationOrderList> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponse.AbstractDataResponseBuilder<Builder, UpdateTransportationOrdersToOngoingResponse, TransportationOrderList> {

        @Override
        protected UpdateTransportationOrdersToOngoingResponse getResponseInstance() {
            return new UpdateTransportationOrdersToOngoingResponse();
        }
    }
}
