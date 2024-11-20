package com.cplerings.core.api.transport.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.api.transport.request.data.TransportationAddressData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTransportationAddressResponse extends AbstractDataResponse<TransportationAddressData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CreateTransportationAddressResponse, TransportationAddressData> {

        @Override
        protected CreateTransportationAddressResponse getResponseInstance() {
            return new CreateTransportationAddressResponse();
        }
    }
}
