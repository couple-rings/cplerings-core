package com.cplerings.core.application.transport.output;

import com.cplerings.core.application.shared.entity.address.ATransportationAddress;

import lombok.Builder;

@Builder
public record CreateTransportationAddressOutput(ATransportationAddress transportationAddress) {
}
