package com.cplerings.core.api.order.data;

import com.cplerings.core.application.shared.entity.order.ACustomOrder;

import lombok.Builder;

@Builder
public record CustomOrderData(ACustomOrder customOrder) {
}
