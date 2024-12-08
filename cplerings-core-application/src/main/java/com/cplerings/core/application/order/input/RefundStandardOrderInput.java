package com.cplerings.core.application.order.input;

import com.cplerings.core.application.order.input.data.RefundStandardOrderInputData;

import lombok.Builder;

@Builder
public record RefundStandardOrderInput(Long standardOrderId, RefundStandardOrderInputData refundStandardOrderRequestData) {

}
