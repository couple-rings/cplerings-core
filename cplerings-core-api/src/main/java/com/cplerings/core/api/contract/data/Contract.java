package com.cplerings.core.api.contract.data;

import com.cplerings.core.application.shared.entity.contract.AContract;

import lombok.Builder;

@Builder
public record Contract(AContract contract) {
}
