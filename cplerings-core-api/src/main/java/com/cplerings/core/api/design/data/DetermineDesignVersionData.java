package com.cplerings.core.api.design.data;

import com.cplerings.core.application.shared.entity.design.ADesignVersion;

import lombok.Builder;

@Builder
public record DetermineDesignVersionData(ADesignVersion femaleDesignVersion, ADesignVersion maleDesignVersion) {
}
