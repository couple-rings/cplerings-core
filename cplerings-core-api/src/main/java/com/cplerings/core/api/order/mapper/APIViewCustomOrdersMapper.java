package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.CustomOrdersData;
import com.cplerings.core.api.order.request.ViewCustomOrdersRequest;
import com.cplerings.core.api.order.response.ViewCustomOrdersResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.order.input.ViewCustomOrdersInput;
import com.cplerings.core.application.order.output.ViewCustomOrdersOutput;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomOrdersMapper extends APIPaginatedMapper<ViewCustomOrdersInput, ViewCustomOrdersOutput, CustomOrdersData, ACustomOrder, ViewCustomOrdersRequest, ViewCustomOrdersResponse> {
}
