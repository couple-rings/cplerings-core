package com.cplerings.core.api.contract.request.data;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetermineContractRequestData {

    private Long signatureId;
    private Instant signedDate;
    private Long documentId;
}
