package com.cplerings.core.api.order.request.data;

import lombok.Builder;

@Builder
public record AssignJewelerToCustomOrderRequestData(Long jewelerId) {
}
