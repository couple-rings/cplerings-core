package com.cplerings.core.api.transport.request.data;

import com.cplerings.core.application.shared.entity.address.ATransportationAddress;

import lombok.Builder;

@Builder
public record TransportationAddressData(ATransportationAddress transportationAddress) {
}
