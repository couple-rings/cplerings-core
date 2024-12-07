package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.RefundData;
import com.cplerings.core.api.order.request.RefundStandardOrderRequest;
import com.cplerings.core.api.order.response.RefundStandardOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.RefundStandardOrderInput;
import com.cplerings.core.application.order.output.RefundStandardOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIRefundStandardOrderMapper extends APIMapper<RefundStandardOrderInput, RefundStandardOrderOutput, RefundData, RefundStandardOrderRequest, RefundStandardOrderResponse> {
}
