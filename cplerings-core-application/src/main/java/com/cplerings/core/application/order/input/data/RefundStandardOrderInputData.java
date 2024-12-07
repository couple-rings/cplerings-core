package com.cplerings.core.application.order.input.data;

import com.cplerings.core.application.shared.entity.order.ARefundMethod;

import lombok.Builder;

@Builder
public record RefundStandardOrderInputData(String reason, Long proofImageId, Long staffId, ARefundMethod refundMethod) {
}
