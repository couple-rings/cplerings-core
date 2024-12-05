package com.cplerings.core.application.payment.output;

import com.cplerings.core.application.shared.entity.payment.APayment;

import lombok.Builder;

@Builder
public record GetPaymentOutput(APayment payment) {
}
