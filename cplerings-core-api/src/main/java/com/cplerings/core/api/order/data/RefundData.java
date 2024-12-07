package com.cplerings.core.api.order.data;

import com.cplerings.core.application.shared.entity.order.ARefund;

import lombok.Builder;

@Builder
public record RefundData(ARefund refund) {
}
