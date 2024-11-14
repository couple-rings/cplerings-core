package com.cplerings.core.application.spouse.input;

import lombok.Builder;

@Builder
public record ViewSpousesOfCustomerInput(Long customerId) {
}
