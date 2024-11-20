package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.transport.output.CreateTransportationAddressOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.address.TransportationAddress;

@Mapper(config = SpringMapperConfiguration.class)
public interface ACreateTransportationAddressMapper {

    CreateTransportationAddressOutput toOutput(TransportationAddress transportationAddress);
}
