package com.cplerings.core.application.design.output;


import com.cplerings.core.application.shared.entity.design.ADesignVersion;

import lombok.Builder;

@Builder
public record CreateDesignVersionOutput(ADesignVersion designVersion) {
}

