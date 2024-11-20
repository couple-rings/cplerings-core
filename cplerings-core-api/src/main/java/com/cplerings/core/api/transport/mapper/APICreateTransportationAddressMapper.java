package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.transport.request.CreateTransportationAddressRequest;
import com.cplerings.core.api.transport.request.data.TransportationAddressData;
import com.cplerings.core.api.transport.response.CreateTransportationAddressResponse;
import com.cplerings.core.application.transport.input.CreateTransportationAddressInput;
import com.cplerings.core.application.transport.output.CreateTransportationAddressOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateTransportationAddressMapper extends APIMapper<CreateTransportationAddressInput, CreateTransportationAddressOutput, TransportationAddressData, CreateTransportationAddressRequest, CreateTransportationAddressResponse> {
}
