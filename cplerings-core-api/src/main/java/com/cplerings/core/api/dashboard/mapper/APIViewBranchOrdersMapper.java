package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewBranchOrdersData;
import com.cplerings.core.api.dashboard.request.ViewBranchOrdersRequest;
import com.cplerings.core.api.dashboard.response.ViewBranchOrdersResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.dashboard.input.ViewBranchOrdersInput;
import com.cplerings.core.application.dashboard.output.ViewBranchOrdersOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewBranchOrdersMapper extends APIMapper<ViewBranchOrdersInput, ViewBranchOrdersOutput, ViewBranchOrdersData, ViewBranchOrdersRequest, ViewBranchOrdersResponse> {
}
