package com.cplerings.core.api.order.request;

import com.cplerings.core.api.order.request.data.RefundStandardOrderRequestData;

import lombok.Builder;

@Builder
public record RefundStandardOrderRequest(Long standardOrderId, RefundStandardOrderRequestData refundStandardOrderRequestData) {
}
