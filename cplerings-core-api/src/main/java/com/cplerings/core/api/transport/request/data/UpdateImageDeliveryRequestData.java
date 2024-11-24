package com.cplerings.core.api.transport.request.data;

import lombok.Builder;

@Builder
public record UpdateImageDeliveryRequestData(Long imageId) {
}
