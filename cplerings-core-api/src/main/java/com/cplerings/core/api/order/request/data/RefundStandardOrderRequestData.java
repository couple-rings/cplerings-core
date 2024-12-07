package com.cplerings.core.api.order.request.data;

import com.cplerings.core.application.shared.entity.order.ARefundMethod;

import lombok.Builder;

@Builder
public record RefundStandardOrderRequestData(String reason, Long proofImageId, Long staffId, ARefundMethod refundMethod) {
}
