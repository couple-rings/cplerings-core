package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.RefundData;
import com.cplerings.core.api.order.request.ViewRefundRequest;
import com.cplerings.core.api.order.response.ViewRefundResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.ViewRefundInput;
import com.cplerings.core.application.order.output.ViewRefundOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewRefundMapper extends APIMapper<ViewRefundInput, ViewRefundOutput, RefundData, ViewRefundRequest, ViewRefundResponse> {
}
