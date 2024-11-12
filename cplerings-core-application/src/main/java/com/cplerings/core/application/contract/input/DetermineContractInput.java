package com.cplerings.core.application.contract.input;

import java.time.Instant;

import lombok.Builder;

@Builder
public record DetermineContractInput(Long contractId, Long signatureId, Instant signedDate, Long documentId) {
}
