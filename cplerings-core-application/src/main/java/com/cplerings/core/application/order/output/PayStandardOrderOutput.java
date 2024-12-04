package com.cplerings.core.application.order.output;

import lombok.Builder;

@Builder
public record PayStandardOrderOutput(Long paymentId, String paymentLink) {

}
