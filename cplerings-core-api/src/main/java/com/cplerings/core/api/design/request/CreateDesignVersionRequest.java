package com.cplerings.core.api.design.request;

import com.cplerings.core.api.design.request.data.CreateDesignVersionRequestData;

import lombok.Builder;

@Builder
public record CreateDesignVersionRequest(CreateDesignVersionRequestData maleVersion, CreateDesignVersionRequestData femaleVersion) {
}
