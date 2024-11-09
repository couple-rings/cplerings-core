package com.cplerings.core.api.design.data;

import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;

import lombok.Builder;

@Builder
public record CustomRequest(ACustomRequest customRequest) {
}
