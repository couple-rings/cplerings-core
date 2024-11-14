package com.cplerings.core.api.transport.response;

import com.cplerings.core.api.shared.AbstractPaginatedResponse;
import com.cplerings.core.api.transport.data.TransportationOrdersData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTransportationOrdersResponse extends AbstractPaginatedResponse<TransportationOrdersData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewTransportationOrdersResponse, TransportationOrdersData> {

        @Override
        protected ViewTransportationOrdersResponse getResponseInstance() {
            return new ViewTransportationOrdersResponse();
        }
    }
}
