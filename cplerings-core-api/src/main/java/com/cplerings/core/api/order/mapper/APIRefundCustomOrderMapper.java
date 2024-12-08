package com.cplerings.core.api.order.mapper;

import com.cplerings.core.api.order.request.RefundCustomOrderRequest;
import com.cplerings.core.api.order.response.RefundCustomOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.RefundCustomOrderInput;
import com.cplerings.core.application.order.output.RefundCustomOrderOutput;
import com.cplerings.core.application.shared.entity.order.ARefundInfo;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIRefundCustomOrderMapper extends APIMapper<RefundCustomOrderInput, RefundCustomOrderOutput, ARefundInfo, RefundCustomOrderRequest, RefundCustomOrderResponse> {

    @Override
    @Mapping(target = "refundDetail", source = "request")
    RefundCustomOrderInput toInput(RefundCustomOrderRequest request);

    @Override
    @Mapping(target = ".", source = "refundInfo")
    ARefundInfo toData(RefundCustomOrderOutput output);
}
