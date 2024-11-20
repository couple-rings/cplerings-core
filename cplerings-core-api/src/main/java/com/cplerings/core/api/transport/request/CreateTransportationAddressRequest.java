package com.cplerings.core.api.transport.request;

import lombok.Builder;

@Builder
public record CreateTransportationAddressRequest(String address, Integer districtCode, String district,
                                                 Long wardCode, String ward, String receiverName, String receiverPhone,
                                                 Long customerId) {
}
