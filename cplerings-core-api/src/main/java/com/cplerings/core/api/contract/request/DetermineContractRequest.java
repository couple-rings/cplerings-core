package com.cplerings.core.api.contract.request;

import java.time.Instant;

import lombok.Builder;

@Builder
public record DetermineContractRequest(Long contractId, Long signatureId, Instant signedDate, Long documentId) {
}
