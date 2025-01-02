package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalOrdersOfBranchData;
import com.cplerings.core.api.dashboard.response.ViewTotalOrdersOfBranchResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.dashboard.output.ViewTotalOrdersOfBranchOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalOrdersOfBranchMapper extends APINoRequestMapper<ViewTotalOrdersOfBranchOutput, ViewTotalOrdersOfBranchData, ViewTotalOrdersOfBranchResponse> {
}
