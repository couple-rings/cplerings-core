package com.cplerings.core.api.transport.request;

import java.util.List;

import lombok.Builder;

@Builder
public record UpdateTransportationOrdersToOngoingRequest(List<Long> transportationOrderIds) {
}
