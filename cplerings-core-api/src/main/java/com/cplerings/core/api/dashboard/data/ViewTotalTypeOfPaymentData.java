package com.cplerings.core.api.dashboard.data;

import lombok.Builder;

@Builder
public record ViewTotalTypeOfPaymentData(Long totalByCash, Long totalByTransfer) {
}
