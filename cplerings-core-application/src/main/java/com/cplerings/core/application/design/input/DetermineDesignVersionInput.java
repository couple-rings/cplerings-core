package com.cplerings.core.application.design.input;

import com.cplerings.core.application.design.input.data.DetermineDesignVersionInputData;

import lombok.Builder;

@Builder
public record DetermineDesignVersionInput(DetermineDesignVersionInputData femaleVersion, DetermineDesignVersionInputData maleVersion) {
}
