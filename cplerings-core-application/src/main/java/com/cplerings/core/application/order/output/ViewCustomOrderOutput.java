package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.order.ACustomOrder;

import lombok.Builder;

@Builder
public record ViewCustomOrderOutput(ACustomOrder customOrder) {
}