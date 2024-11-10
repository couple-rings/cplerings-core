package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;

import lombok.Builder;

@Builder
public record DetermineCustomRequestInput(Long customRequestId, ACustomRequestStatus customRequestStatus,
                                          Long staffId) {
}
