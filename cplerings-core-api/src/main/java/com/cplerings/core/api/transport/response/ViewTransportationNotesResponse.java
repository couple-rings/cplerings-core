package com.cplerings.core.api.transport.response;

import com.cplerings.core.api.metal.data.MetalSpecification;
import com.cplerings.core.api.metal.response.ViewMetalSpecificationResponse;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;
import com.cplerings.core.api.transport.data.TransportationNotesData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTransportationNotesResponse extends AbstractPaginatedResponse<TransportationNotesData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewTransportationNotesResponse, TransportationNotesData> {

        @Override
        protected ViewTransportationNotesResponse getResponseInstance() {
            return new ViewTransportationNotesResponse();
        }
    }
}
