package com.cplerings.core.api.spouse.request.data;

import lombok.Builder;

@Builder
public record VerifyResidentIdRequestData(Long customerId) {
}
