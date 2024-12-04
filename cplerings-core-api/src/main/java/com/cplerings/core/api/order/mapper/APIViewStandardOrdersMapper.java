package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.StandardOrdersData;
import com.cplerings.core.api.order.request.ViewStandardOrdersRequest;
import com.cplerings.core.api.order.response.ViewStandardOrdersResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.order.input.ViewStandardOrdersInput;
import com.cplerings.core.application.order.output.ViewStandardOrdersOutput;
import com.cplerings.core.application.shared.entity.order.AStandardOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewStandardOrdersMapper extends APIPaginatedMapper<ViewStandardOrdersInput, ViewStandardOrdersOutput, StandardOrdersData, AStandardOrder, ViewStandardOrdersRequest, ViewStandardOrdersResponse> {
}
