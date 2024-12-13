package com.cplerings.core.application.diamond.output;

import com.cplerings.core.application.shared.entity.design.ADiamond;

import lombok.Builder;

@Builder
public record DisableDiamondOutput(ADiamond diamond) {
}
