package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.request.UpdateTransportationOrderStatusRequest;
import com.cplerings.core.api.transport.response.UpdateTransportationOrderStatusResponse;
import com.cplerings.core.application.transport.input.UpdateTransportationOrderStatusInput;
import com.cplerings.core.application.transport.output.UpdateTransportationOrderStatusOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIUpdateTransportationOrderStatusMapper extends APIMapper<UpdateTransportationOrderStatusInput, UpdateTransportationOrderStatusOutput, TransportationOrder, UpdateTransportationOrderStatusRequest, UpdateTransportationOrderStatusResponse> {
}
