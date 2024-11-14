package com.cplerings.core.application.transport.input;

import java.util.List;

import lombok.Builder;

@Builder
public record UpdateTransportationOrdersToOngoingInput(List<Long> transportationOrderIds) {
}
