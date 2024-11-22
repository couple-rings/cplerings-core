package com.cplerings.core.infrastructure.service.payment.vnpay;

import com.cplerings.core.domain.shared.valueobject.Money;

import java.math.BigDecimal;

public abstract class AbstractVNPayPaymentService {

    protected final Long toAmountWithConversion(Money amount) {
        if (amount == null) {
            return 0L;
        }
        return Long.valueOf(amount.getAmount()
                .multiply(BigDecimal.valueOf(100L))
                .toBigInteger()
                .toString());
    }

    protected final Long toAmountWithoutConversion(Money amount) {
        if (amount == null) {
            return 0L;
        }
        return Long.valueOf(amount.getAmount()
                .toBigInteger()
                .toString());
    }
}
