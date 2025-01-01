package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewRefundOrdersWithDateData;
import com.cplerings.core.api.dashboard.request.ViewRefundOrdersWithDateRequest;
import com.cplerings.core.api.dashboard.response.ViewRefundOrdersWithDateResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.dashboard.datasource.data.CombinedOrder;
import com.cplerings.core.application.dashboard.input.ViewRefundOrdersWithDateInput;
import com.cplerings.core.application.dashboard.output.ViewRefundOrdersWithDateOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewRefundOrdersWithDateMapper extends APIPaginatedMapper<ViewRefundOrdersWithDateInput, ViewRefundOrdersWithDateOutput, ViewRefundOrdersWithDateData, CombinedOrder, ViewRefundOrdersWithDateRequest, ViewRefundOrdersWithDateResponse> {
}
