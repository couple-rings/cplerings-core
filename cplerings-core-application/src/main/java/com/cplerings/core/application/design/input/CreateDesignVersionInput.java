package com.cplerings.core.application.design.input;

import com.cplerings.core.application.design.input.data.CreateDesignVersionInputData;

import lombok.Builder;

@Builder
public record CreateDesignVersionInput(CreateDesignVersionInputData maleVersion, CreateDesignVersionInputData femaleVersion) {
}
