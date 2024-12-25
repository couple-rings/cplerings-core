package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.order.AResellOrder;

import lombok.Builder;

@Builder
public record ViewResellOrderOutput(AResellOrder resellOrder) {
}
