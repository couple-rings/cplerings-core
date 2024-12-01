package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.order.AStandardOrder;

import lombok.Builder;

@Builder
public record CreateStandardOrderOutput(AStandardOrder standardOrder) {
}
