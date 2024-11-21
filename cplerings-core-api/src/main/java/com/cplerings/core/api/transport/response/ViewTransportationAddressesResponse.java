package com.cplerings.core.api.transport.response;

import com.cplerings.core.api.shared.AbstractPaginatedResponse;
import com.cplerings.core.api.transport.data.TransportationAddressesData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTransportationAddressesResponse extends AbstractPaginatedResponse<TransportationAddressesData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewTransportationAddressesResponse, TransportationAddressesData> {

        @Override
        protected ViewTransportationAddressesResponse getResponseInstance() {
            return new ViewTransportationAddressesResponse();
        }
    }
}
