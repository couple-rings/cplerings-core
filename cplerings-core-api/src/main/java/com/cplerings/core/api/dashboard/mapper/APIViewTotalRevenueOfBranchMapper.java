package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalRevenueOfBranchData;
import com.cplerings.core.api.dashboard.response.ViewTotalRevenueResponse;
import com.cplerings.core.api.shared.NoRequest;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.dashboard.output.ViewTotalRevenueOutput;
import com.cplerings.core.application.shared.input.NoInput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalRevenueOfBranchMapper extends APIMapper<NoInput, ViewTotalRevenueOutput, ViewTotalRevenueOfBranchData, NoRequest, ViewTotalRevenueResponse> {
}
