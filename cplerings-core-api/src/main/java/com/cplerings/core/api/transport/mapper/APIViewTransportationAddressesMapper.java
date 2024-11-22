package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.api.transport.data.TransportationAddressesData;
import com.cplerings.core.api.transport.request.ViewTransportationAddressesRequest;
import com.cplerings.core.api.transport.response.ViewTransportationAddressesResponse;
import com.cplerings.core.application.shared.entity.address.ATransportationAddress;
import com.cplerings.core.application.transport.input.ViewTransportationAddressesInput;
import com.cplerings.core.application.transport.output.ViewTransportationAddressesOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTransportationAddressesMapper extends APIPaginatedMapper<ViewTransportationAddressesInput, ViewTransportationAddressesOutput, TransportationAddressesData, ATransportationAddress, ViewTransportationAddressesRequest, ViewTransportationAddressesResponse> {
}
