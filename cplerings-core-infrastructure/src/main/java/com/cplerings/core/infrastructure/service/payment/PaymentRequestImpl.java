package com.cplerings.core.infrastructure.service.payment;

import com.cplerings.core.application.shared.service.payment.PaymentRequest;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class PaymentRequestImpl implements PaymentRequest {

    private PaymentType paymentType;
    private String paymentLink;
    private Payment payment;
}
