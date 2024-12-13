package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.RefundsData;
import com.cplerings.core.api.order.request.ViewRefundOrdersRequest;
import com.cplerings.core.api.order.response.ViewRefundOrdersResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.order.input.ViewRefundOrdersInput;
import com.cplerings.core.application.order.output.ViewRefundOrdersOutput;
import com.cplerings.core.application.shared.entity.order.ARefund;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewRefundOrdersMapper extends APIPaginatedMapper<ViewRefundOrdersInput, ViewRefundOrdersOutput, RefundsData, ARefund, ViewRefundOrdersRequest, ViewRefundOrdersResponse> {
}
