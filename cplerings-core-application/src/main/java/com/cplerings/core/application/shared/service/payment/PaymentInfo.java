package com.cplerings.core.application.shared.service.payment;

import com.cplerings.core.domain.shared.valueobject.Money;

public interface PaymentInfo {

    Money getAmount();

    String getDescription();
}
