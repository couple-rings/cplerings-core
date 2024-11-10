package com.cplerings.core.api.design.request;

import lombok.Builder;

@Builder
public record DetermineDesignVersionRequest(Long designVersionId) {
}
