package com.cplerings.core.application.jewelry.output;

import com.cplerings.core.application.shared.entity.order.AResellOrder;

import lombok.Builder;

@Builder
public record ResellJewelryOutput(AResellOrder resellOrder) {

}
