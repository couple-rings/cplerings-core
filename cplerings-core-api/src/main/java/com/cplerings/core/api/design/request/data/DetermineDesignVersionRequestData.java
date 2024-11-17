package com.cplerings.core.api.design.request.data;

import com.cplerings.core.application.shared.entity.design.ADesignVersionOwner;

import lombok.Builder;

@Builder
public record DetermineDesignVersionRequestData(ADesignVersionOwner owner, Boolean isAccepted) {
}
