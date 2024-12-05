package com.cplerings.core.application.payment.input;

import lombok.Builder;

@Builder
public record GetPaymentInput(Long paymentId) {
}
