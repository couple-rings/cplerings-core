package com.cplerings.core.application.contract.input;

import java.time.Instant;

import lombok.Builder;

@Builder
public record DetermineContractInput(Long contractId, String signature, Instant signedDate, Long documentId) {
}
