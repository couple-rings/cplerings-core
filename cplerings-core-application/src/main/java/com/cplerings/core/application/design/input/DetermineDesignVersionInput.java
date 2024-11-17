package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.entity.design.ADesignVersionOwner;

import lombok.Builder;

@Builder
public record DetermineDesignVersionInput(Long designVersionId, ADesignVersionOwner owner, Boolean isAccepted) {
}
