package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewCustomOrdersWithDateData;
import com.cplerings.core.api.dashboard.request.ViewCustomOrdersWithDateRequest;
import com.cplerings.core.api.dashboard.response.ViewCustomOrdersWithDateResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.dashboard.input.ViewCustomOrdersWithDateInput;
import com.cplerings.core.application.dashboard.output.ViewCustomOrdersWithDateOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomOrdersWithDateMapper extends APIPaginatedMapper<ViewCustomOrdersWithDateInput, ViewCustomOrdersWithDateOutput, ViewCustomOrdersWithDateData, CombinedOrder, ViewCustomOrdersWithDateRequest, ViewCustomOrdersWithDateResponse> {
}
