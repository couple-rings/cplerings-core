package com.cplerings.core.api.design.request;

import com.cplerings.core.api.design.request.data.DetermineDesignVersionRequestData;

import lombok.Builder;

@Builder
public record DetermineDesignVersionRequest(DetermineDesignVersionRequestData femaleVersion, DetermineDesignVersionRequestData maleVersion) {
}
