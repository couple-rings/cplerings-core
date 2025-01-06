package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalInData;
import com.cplerings.core.api.dashboard.request.ViewTotalInRequest;
import com.cplerings.core.api.dashboard.response.ViewTotalInResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.dashboard.input.ViewTotalInInput;
import com.cplerings.core.application.dashboard.output.ViewTotalInOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalInMapper extends APIMapper<ViewTotalInInput, ViewTotalInOutput, ViewTotalInData, ViewTotalInRequest, ViewTotalInResponse> {
}
