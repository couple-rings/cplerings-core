package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.request.RefundCustomOrderRequest;
import com.cplerings.core.api.order.response.RefundCustomOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.RefundCustomOrderInput;
import com.cplerings.core.application.order.output.RefundCustomOrderOutput;
import com.cplerings.core.application.shared.entity.order.ARefund;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIRefundCustomOrderMapper extends APIMapper<RefundCustomOrderInput, RefundCustomOrderOutput, ARefund, RefundCustomOrderRequest, RefundCustomOrderResponse> {

}
