package com.cplerings.core.application.shared.service.payment;

import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentInfo {

    private Money amount;
    private String description;
    private PaymentReceiverType receiverType;
}
