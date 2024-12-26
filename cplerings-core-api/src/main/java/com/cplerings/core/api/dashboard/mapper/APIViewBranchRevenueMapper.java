package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewBranchRevenueData;
import com.cplerings.core.api.dashboard.request.ViewBranchRevenueRequest;
import com.cplerings.core.api.dashboard.response.ViewBranchRevenueResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.dashboard.input.ViewBranchRevenueInput;
import com.cplerings.core.application.dashboard.output.ViewBranchRevenueOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewBranchRevenueMapper extends APIMapper<ViewBranchRevenueInput, ViewBranchRevenueOutput, ViewBranchRevenueData, ViewBranchRevenueRequest, ViewBranchRevenueResponse> {
}
