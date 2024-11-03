package com.cplerings.core.application.design.input;

import lombok.Builder;

@Builder
public record UpdateDesignVersionStatusInput(Long designVersionId) {
}
