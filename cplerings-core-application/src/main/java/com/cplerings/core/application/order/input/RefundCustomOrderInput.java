package com.cplerings.core.application.order.input;

import com.cplerings.core.application.order.input.data.RefundDetail;

import lombok.Builder;

@Builder
public record RefundCustomOrderInput(Long customOrderId, RefundDetail refundDetail) {

}
