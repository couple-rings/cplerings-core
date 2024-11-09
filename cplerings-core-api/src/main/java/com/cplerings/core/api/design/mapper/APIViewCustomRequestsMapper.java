package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.CustomRequestsData;
import com.cplerings.core.api.design.request.ViewCustomRequestsRequest;
import com.cplerings.core.api.design.response.ViewCustomRequestsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.design.input.ViewCustomRequestsInput;
import com.cplerings.core.application.design.output.ViewCustomRequestsOutput;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomRequestsMapper extends APIPaginatedMapper<ViewCustomRequestsInput, ViewCustomRequestsOutput, CustomRequestsData, ACustomRequest, ViewCustomRequestsRequest, ViewCustomRequestsResponse> {
}
