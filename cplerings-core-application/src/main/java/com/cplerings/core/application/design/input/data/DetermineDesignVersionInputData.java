package com.cplerings.core.application.design.input.data;

import com.cplerings.core.application.shared.entity.design.ADesignVersionOwner;

import lombok.Builder;

@Builder
public record DetermineDesignVersionInputData(Long designVersionId, ADesignVersionOwner owner) {
}
