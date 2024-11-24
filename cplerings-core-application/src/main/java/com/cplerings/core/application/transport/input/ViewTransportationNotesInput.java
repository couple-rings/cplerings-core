package com.cplerings.core.application.transport.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTransportationNotesInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewTransportationNotesInput> {

        @Override
        protected ViewTransportationNotesInput getRequestInstance() {
            return new ViewTransportationNotesInput();
        }
    }
}
