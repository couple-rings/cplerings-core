package com.cplerings.core.application.transport.datasource;

import com.cplerings.core.application.transport.datasource.result.TransportationNotes;
import com.cplerings.core.application.transport.input.ViewTransportationNotesInput;

public interface ViewTransportationNotesDataSource {

    TransportationNotes getTransportationNotes(ViewTransportationNotesInput input);
}
