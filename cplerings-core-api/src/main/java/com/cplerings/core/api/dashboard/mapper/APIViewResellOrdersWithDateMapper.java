package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewResellOrdersWithDateData;
import com.cplerings.core.api.dashboard.request.ViewResellOrdersWithDateRequest;
import com.cplerings.core.api.dashboard.response.ViewResellOrdersWithDateResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.dashboard.input.ViewResellOrdersWithDateInput;
import com.cplerings.core.application.dashboard.output.ViewResellOrdersWithDateOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewResellOrdersWithDateMapper extends APIPaginatedMapper<ViewResellOrdersWithDateInput, ViewResellOrdersWithDateOutput, ViewResellOrdersWithDateData, CombinedOrder, ViewResellOrdersWithDateRequest, ViewResellOrdersWithDateResponse> {
}
