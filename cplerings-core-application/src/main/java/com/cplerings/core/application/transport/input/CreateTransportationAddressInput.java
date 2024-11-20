package com.cplerings.core.application.transport.input;

import lombok.Builder;

@Builder
public record CreateTransportationAddressInput(String address, Integer districtCode, String district,
                                               Long wardCode, String ward, String receiverName, String receiverPhone,
                                               Long customerId) {
}
