package com.cplerings.core.application.design.input;

import lombok.Builder;

@Builder
public record CancelCustomRequestInput(Long customRequestId) {
}