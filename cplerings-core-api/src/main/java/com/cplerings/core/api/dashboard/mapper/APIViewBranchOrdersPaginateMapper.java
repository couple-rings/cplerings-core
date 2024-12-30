package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewBranchOrdersPaginateData;
import com.cplerings.core.api.dashboard.request.ViewBranchOrdersPaginateRequest;
import com.cplerings.core.api.dashboard.response.ViewBranchOrdersPaginateResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.dashboard.input.ViewBranchOrdersPaginateInput;
import com.cplerings.core.application.dashboard.output.ViewBranchOrdersPaginateOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewBranchOrdersPaginateMapper extends APIPaginatedMapper<ViewBranchOrdersPaginateInput, ViewBranchOrdersPaginateOutput, ViewBranchOrdersPaginateData, CombinedOrder, ViewBranchOrdersPaginateRequest, ViewBranchOrdersPaginateResponse> {
}
