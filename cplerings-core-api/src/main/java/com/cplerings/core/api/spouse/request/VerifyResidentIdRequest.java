package com.cplerings.core.api.spouse.request;

import lombok.Builder;

@Builder
public record VerifyResidentIdRequest(String citizenId, Long customerId) {
}
