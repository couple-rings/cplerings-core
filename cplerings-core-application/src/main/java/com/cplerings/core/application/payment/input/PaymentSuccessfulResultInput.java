package com.cplerings.core.application.payment.input;

import com.cplerings.core.domain.payment.Payment;

import lombok.Builder;

@Builder
public record PaymentSuccessfulResultInput(Payment payment) {

}
