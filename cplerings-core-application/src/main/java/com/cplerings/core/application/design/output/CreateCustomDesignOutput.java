package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.ACustomDesign;

import lombok.Builder;

@Builder
public record CreateCustomDesignOutput(ACustomDesign customDesign) {
}
