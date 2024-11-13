package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.request.AssignTransportOrderRequest;
import com.cplerings.core.api.transport.response.AssignTransportOrderResponse;
import com.cplerings.core.application.transport.input.AssignTransportOrderInput;
import com.cplerings.core.application.transport.output.AssignTransportOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIAssignTransportationOrderMapper extends APIMapper<AssignTransportOrderInput, AssignTransportOrderOutput, TransportationOrder, AssignTransportOrderRequest, AssignTransportOrderResponse> {
}
