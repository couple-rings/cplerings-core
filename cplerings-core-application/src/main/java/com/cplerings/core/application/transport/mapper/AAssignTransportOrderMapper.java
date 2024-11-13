package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.transport.output.AssignTransportOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.TransportationOrder;

@Mapper(config = SpringMapperConfiguration.class)
public interface AAssignTransportOrderMapper {

    AssignTransportOrderOutput toOutput(TransportationOrder transportationOrder);
}
