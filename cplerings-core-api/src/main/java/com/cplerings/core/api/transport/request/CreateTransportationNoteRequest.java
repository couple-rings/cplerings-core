package com.cplerings.core.api.transport.request;

import lombok.Builder;

@Builder
public record CreateTransportationNoteRequest(Long transportationOrderId, String note) {
}
