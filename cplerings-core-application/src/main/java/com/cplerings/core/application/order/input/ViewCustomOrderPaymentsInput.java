package com.cplerings.core.application.order.input;

import lombok.Builder;

@Builder
public record ViewCustomOrderPaymentsInput(Long customOrderId) {

}
