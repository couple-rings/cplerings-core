package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.order.ARefund;

import lombok.Builder;

@Builder
public record RefundStandardOrderOutput(ARefund refund) {
}
