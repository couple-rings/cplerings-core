package com.cplerings.core.application.jewelry.output;

import com.cplerings.core.application.shared.entity.jewelry.AJewelry;

import lombok.Builder;

@Builder
public record CreateJewelryOutput(AJewelry jewelry) {
}
