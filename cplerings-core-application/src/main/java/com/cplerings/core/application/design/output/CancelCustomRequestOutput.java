package com.cplerings.core.application.design.output;

import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;

import lombok.Builder;

@Builder
public record CancelCustomRequestOutput(ACustomRequest customRequest) {
}
