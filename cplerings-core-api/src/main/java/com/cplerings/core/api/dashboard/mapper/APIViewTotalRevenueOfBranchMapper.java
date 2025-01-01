package com.cplerings.core.api.dashboard.mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalRevenueOfBranchData;
import com.cplerings.core.api.dashboard.response.ViewTotalRevenueResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.dashboard.output.ViewTotalRevenueOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalRevenueOfBranchMapper extends APINoRequestMapper<ViewTotalRevenueOutput, ViewTotalRevenueOfBranchData, ViewTotalRevenueResponse> {

}
