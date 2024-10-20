package com.cplerings.core.application.shared.service.payment;

public interface PaymentVerificationService<INPUT> {

    boolean paymentIsValid(INPUT input);
}
