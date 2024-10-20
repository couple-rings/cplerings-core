package com.cplerings.core.infrastructure.service.payment;

import com.cplerings.core.application.shared.service.payment.PaymentInfo;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class PaymentInfoImpl implements PaymentInfo {

    private Money amount;
    private String description;
}
