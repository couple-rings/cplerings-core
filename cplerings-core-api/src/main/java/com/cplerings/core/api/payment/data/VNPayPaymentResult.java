package com.cplerings.core.api.payment.data;

import com.cplerings.core.application.shared.entity.payment.APaymentStatus;

import lombok.Builder;

@Builder
public record VNPayPaymentResult(APaymentStatus paymentStatus) {

}
