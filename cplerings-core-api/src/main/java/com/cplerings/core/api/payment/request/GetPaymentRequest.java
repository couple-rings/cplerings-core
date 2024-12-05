package com.cplerings.core.api.payment.request;

import lombok.Builder;

@Builder
public record GetPaymentRequest(Long paymentId) {
}
