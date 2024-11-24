package com.cplerings.core.application.transport.input;

import lombok.Builder;

@Builder
public record CreateTransportationNoteInput(Long transportationOrderId, String note) {
}
