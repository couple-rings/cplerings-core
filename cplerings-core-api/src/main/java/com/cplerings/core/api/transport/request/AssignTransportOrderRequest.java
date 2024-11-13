package com.cplerings.core.api.transport.request;

import lombok.Builder;

@Builder
public record AssignTransportOrderRequest(Long transportOrderId, Long transporterId) {

}
