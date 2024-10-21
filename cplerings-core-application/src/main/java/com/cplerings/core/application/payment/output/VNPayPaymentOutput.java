package com.cplerings.core.application.payment.output;

import com.cplerings.core.application.shared.entity.payment.APaymentStatus;

import lombok.Builder;

@Builder
public record VNPayPaymentOutput(APaymentStatus paymentStatus) {

}
