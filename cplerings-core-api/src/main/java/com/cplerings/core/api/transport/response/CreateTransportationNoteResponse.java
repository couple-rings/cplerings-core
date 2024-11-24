package com.cplerings.core.api.transport.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.api.transport.request.data.TransportationAddressData;
import com.cplerings.core.application.shared.entity.transport.ATransportationNote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTransportationNoteResponse extends AbstractDataResponse<ATransportationNote> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CreateTransportationNoteResponse, ATransportationNote> {

        @Override
        protected CreateTransportationNoteResponse getResponseInstance() {
            return new CreateTransportationNoteResponse();
        }
    }
}
