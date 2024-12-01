package com.cplerings.core.application.order.input;

import java.util.List;

import lombok.Builder;

@Builder
public record CreateStandardOrderInput(Long customerId, List<Long> jewelryIds, Long transportationAddressId) {
}
