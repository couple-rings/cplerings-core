package com.cplerings.core.api.order.request.data;

import lombok.Builder;

@Builder
public record RefundStandardOrderRequestData(String reason, Long proofImageId, Long staffId) {
}
