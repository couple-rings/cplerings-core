package com.cplerings.core.api.branch.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.branch.data.BranchesData;
import com.cplerings.core.api.branch.request.ViewBranchesRequest;
import com.cplerings.core.api.branch.response.ViewBranchesResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.branch.input.ViewBranchesInput;
import com.cplerings.core.application.branch.output.ViewBranchesOutput;
import com.cplerings.core.application.shared.entity.branch.ABranch;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewBranchesMapper extends APIPaginatedMapper<ViewBranchesInput, ViewBranchesOutput, BranchesData, ABranch, ViewBranchesRequest, ViewBranchesResponse> {
}