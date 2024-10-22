package com.cplerings.core.api.design.data;

import com.cplerings.core.domain.metal.MetalColor;
import com.cplerings.core.domain.shared.valueobject.Money;

public record MetalSpecificationInformation(String name, Money pricePerUnit, MetalColor color) {
}
