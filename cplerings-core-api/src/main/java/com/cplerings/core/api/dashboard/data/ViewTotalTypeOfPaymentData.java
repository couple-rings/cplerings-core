package com.cplerings.core.api.dashboard.data;

import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;

@Builder
public record ViewTotalTypeOfPaymentData(Money totalByCash, Money totalByTransfer) {
}
