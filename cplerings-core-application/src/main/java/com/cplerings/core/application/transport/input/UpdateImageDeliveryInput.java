package com.cplerings.core.application.transport.input;

import lombok.Builder;

@Builder
public record UpdateImageDeliveryInput(Long transportationOrderId, Long imageId) {
}
