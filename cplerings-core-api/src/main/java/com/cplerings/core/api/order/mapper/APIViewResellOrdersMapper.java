package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.ResellOrdersData;
import com.cplerings.core.api.order.request.ViewResellOrdersRequest;
import com.cplerings.core.api.order.response.ViewResellOrdersResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.order.input.ViewResellOrdersInput;
import com.cplerings.core.application.order.output.ViewResellOrdersOutput;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewResellOrdersMapper extends APIPaginatedMapper<ViewResellOrdersInput, ViewResellOrdersOutput, ResellOrdersData, AResellOrder, ViewResellOrdersRequest, ViewResellOrdersResponse> {
}
