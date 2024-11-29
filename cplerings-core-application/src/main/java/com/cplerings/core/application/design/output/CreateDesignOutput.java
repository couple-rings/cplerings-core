package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.ADesign;

import lombok.Builder;

@Builder
public record CreateDesignOutput(ADesign design) {
}
