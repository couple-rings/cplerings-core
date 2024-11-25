package com.cplerings.core.application.spouse.input;

import lombok.Builder;

@Builder
public record VerifyResidentIdInput(String citizenId, Long customerId) {
}
