package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.api.transport.data.TransportationOrdersData;
import com.cplerings.core.api.transport.request.ViewTransportationOrdersRequest;
import com.cplerings.core.api.transport.response.ViewTransportationOrdersResponse;
import com.cplerings.core.application.shared.entity.order.ATransportationOrder;
import com.cplerings.core.application.transport.input.ViewTransportationOrdersInput;
import com.cplerings.core.application.transport.output.ViewTransportationOrdersOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTransportationOrdersMapper extends APIPaginatedMapper<ViewTransportationOrdersInput, ViewTransportationOrdersOutput, TransportationOrdersData, ATransportationOrder, ViewTransportationOrdersRequest, ViewTransportationOrdersResponse> {
}
