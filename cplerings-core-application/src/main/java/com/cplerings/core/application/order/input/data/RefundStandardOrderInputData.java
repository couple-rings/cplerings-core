package com.cplerings.core.application.order.input.data;

import lombok.Builder;

@Builder
public record RefundStandardOrderInputData(String reason, Long proofImageId, Long staffId) {
}
