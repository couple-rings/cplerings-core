package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.transport.data.TransportationOrder;
import com.cplerings.core.api.transport.request.ViewTransportationOrderRequest;
import com.cplerings.core.api.transport.response.ViewTransportationOrderResponse;
import com.cplerings.core.application.transport.input.ViewTransportationOrderInput;
import com.cplerings.core.application.transport.output.ViewTransportationOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTransportationOrderMapper extends APIMapper<ViewTransportationOrderInput, ViewTransportationOrderOutput, TransportationOrder, ViewTransportationOrderRequest, ViewTransportationOrderResponse> {
}
