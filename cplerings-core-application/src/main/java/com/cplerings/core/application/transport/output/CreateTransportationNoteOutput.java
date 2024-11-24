package com.cplerings.core.application.transport.output;

import com.cplerings.core.application.shared.entity.transport.ATransportationNote;

import lombok.Builder;

@Builder
public record CreateTransportationNoteOutput(ATransportationNote transportationNote) {
}
