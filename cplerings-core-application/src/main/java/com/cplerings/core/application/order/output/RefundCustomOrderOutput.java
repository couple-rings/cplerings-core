package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.order.ARefundInfo;

import lombok.Builder;

@Builder
public record RefundCustomOrderOutput(ARefundInfo refundInfo) {

}
