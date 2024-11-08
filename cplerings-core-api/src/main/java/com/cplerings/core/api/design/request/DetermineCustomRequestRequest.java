package com.cplerings.core.api.design.request;

import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;

import lombok.Builder;

@Builder
public record DetermineCustomRequestRequest(Long customRequestId, ACustomRequestStatus customRequestStatus, String comment) {
}
