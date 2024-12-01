package com.cplerings.core.api.order.request;

import java.util.List;

import lombok.Builder;

@Builder
public record CreateStandardOrderRequest(Long customerId, List<Long> jewelryIds, Long transportationAddressId) {
}
