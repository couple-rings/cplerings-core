package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.transport.datasource.result.TransportationAddresses;
import com.cplerings.core.application.transport.output.ViewTransportationAddressesOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewTransportationAddressesMapper {

    @Mapping(target = "items", source = "transportationAddresses")
    ViewTransportationAddressesOutput toOutput(TransportationAddresses transportationAddresses);
}
