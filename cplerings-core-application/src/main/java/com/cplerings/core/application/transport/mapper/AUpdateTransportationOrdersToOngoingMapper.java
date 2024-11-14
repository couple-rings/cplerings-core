package com.cplerings.core.application.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.transport.output.UpdateTransportationOrdersToOngoingOutput;
import com.cplerings.core.application.transport.output.data.TransportationOrderList;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AUpdateTransportationOrdersToOngoingMapper {

    UpdateTransportationOrdersToOngoingOutput toOutput(TransportationOrderList transportationOrders);
}
