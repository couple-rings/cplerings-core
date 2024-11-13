package com.cplerings.core.application.transport.input;

import lombok.Builder;

@Builder
public record AssignTransportOrderInput(Long transportOrderId, Long transporterId) {
}
