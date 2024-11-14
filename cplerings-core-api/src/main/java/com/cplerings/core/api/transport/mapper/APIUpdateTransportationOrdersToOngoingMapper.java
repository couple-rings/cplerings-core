package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.transport.data.TransportationOrderList;
import com.cplerings.core.api.transport.request.UpdateTransportationOrdersToOngoingRequest;
import com.cplerings.core.api.transport.response.UpdateTransportationOrdersToOngoingResponse;
import com.cplerings.core.application.transport.input.UpdateTransportationOrdersToOngoingInput;
import com.cplerings.core.application.transport.output.UpdateTransportationOrdersToOngoingOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIUpdateTransportationOrdersToOngoingMapper extends APIMapper<UpdateTransportationOrdersToOngoingInput, UpdateTransportationOrdersToOngoingOutput, TransportationOrderList, UpdateTransportationOrdersToOngoingRequest, UpdateTransportationOrdersToOngoingResponse> {
}
