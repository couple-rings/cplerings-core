package com.cplerings.core.api.contract.request;

import java.time.Instant;

import lombok.Builder;

@Builder
public record DetermineContractRequest(Long contractId, String signature, Instant signedDate, Long documentId) {
}
