package com.cplerings.core.application.contract.output;

import com.cplerings.core.application.shared.entity.contract.AContract;

import lombok.Builder;

@Builder
public record DetermineContractOutput(AContract contract) {
}
