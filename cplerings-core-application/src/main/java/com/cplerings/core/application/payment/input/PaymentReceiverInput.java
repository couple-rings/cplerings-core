package com.cplerings.core.application.payment.input;

import com.cplerings.core.domain.payment.PaymentReceiver;

import lombok.Builder;

@Builder
public record PaymentReceiverInput(PaymentReceiver paymentReceiver) {

}
