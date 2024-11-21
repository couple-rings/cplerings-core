package com.cplerings.core.application.transport.datasource.result;

import java.util.List;

import com.cplerings.core.domain.address.TransportationAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransportationAddresses {

    private List<TransportationAddress> transportationAddresses;
    private long count;
    private int page;
    private int pageSize;
}
