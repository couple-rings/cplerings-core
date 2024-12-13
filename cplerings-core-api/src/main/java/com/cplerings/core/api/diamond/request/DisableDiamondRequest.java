package com.cplerings.core.api.diamond.request;

import lombok.Builder;

@Builder
public record DisableDiamondRequest(Long diamondId) {
}
