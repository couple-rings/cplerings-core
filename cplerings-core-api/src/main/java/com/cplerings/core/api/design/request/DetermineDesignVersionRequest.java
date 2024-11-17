package com.cplerings.core.api.design.request;

import com.cplerings.core.application.shared.entity.design.ADesignVersionOwner;

import lombok.Builder;

@Builder
public record DetermineDesignVersionRequest(Long designVersionId, ADesignVersionOwner owner, Boolean isAccepted) {
}
