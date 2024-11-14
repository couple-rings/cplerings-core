package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.transport.datasource.result.TransportationOrders;
import com.cplerings.core.application.transport.output.ViewTransportationOrdersOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewTransportationOrdersMapper {

    @Mapping(target = "items", source = "transportationOrders")
    ViewTransportationOrdersOutput toOutput(TransportationOrders transportationOrders);
}
