package com.cplerings.core.application.shared.service.payment;

import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentType;

public interface PaymentRequest {

    PaymentType getPaymentType();

    String getPaymentLink();

    Payment getPayment();
}
