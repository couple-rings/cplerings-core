package com.cplerings.core.api.design.request;

import lombok.Builder;

@Builder
public record ViewDesignVersionRequest(Long designVersionId) {
}
