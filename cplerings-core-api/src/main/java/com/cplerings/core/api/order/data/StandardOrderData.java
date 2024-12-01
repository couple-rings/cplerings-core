package com.cplerings.core.api.order.data;

import com.cplerings.core.application.shared.entity.order.AStandardOrder;

import lombok.Builder;

@Builder
public record StandardOrderData(AStandardOrder standardOrder) {
}
