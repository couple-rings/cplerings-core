package com.cplerings.core.application.transport.output;

import com.cplerings.core.application.shared.entity.transport.ATransportationNote;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTransportationNotesOutput extends AbstractPaginatedOutput<ATransportationNote> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewTransportationNotesOutput, ATransportationNote> {

        @Override
        protected ViewTransportationNotesOutput getOutputInstance() {
            return new ViewTransportationNotesOutput();
        }
    }
}
