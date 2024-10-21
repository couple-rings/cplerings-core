package com.cplerings.core.application.shared.service.payment;

import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentRequest {

    private PaymentType paymentType;
    private String paymentLink;
    private Payment payment;
}
