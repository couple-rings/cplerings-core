package com.cplerings.core.api.transport.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ViewTransportationAddressesRequest extends AbstractPaginatedRequest {

    private Long customerId;
}
