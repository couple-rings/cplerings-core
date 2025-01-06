package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalExpenditureData;
import com.cplerings.core.api.dashboard.request.ViewTotalExpenditureRequest;
import com.cplerings.core.api.dashboard.response.ViewTotalExpenditureResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.dashboard.input.ViewTotalExpenditureInput;
import com.cplerings.core.application.dashboard.output.ViewTotalExpenditureOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalExpenditureMapper extends APIMapper<ViewTotalExpenditureInput, ViewTotalExpenditureOutput, ViewTotalExpenditureData, ViewTotalExpenditureRequest, ViewTotalExpenditureResponse> {
}
