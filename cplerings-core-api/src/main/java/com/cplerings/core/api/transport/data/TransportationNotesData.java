package com.cplerings.core.api.transport.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.transport.ATransportationNote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransportationNotesData extends AbstractPaginatedData<ATransportationNote> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, TransportationNotesData, ATransportationNote> {

        @Override
        protected TransportationNotesData getDataInstance() {
            return new TransportationNotesData();
        }
    }
}
