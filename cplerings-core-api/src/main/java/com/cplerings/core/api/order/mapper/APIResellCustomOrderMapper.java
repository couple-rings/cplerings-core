package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.order.request.ResellCustomOrderRequest;
import com.cplerings.core.api.order.response.ResellCustomOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.ResellCustomOrderInput;
import com.cplerings.core.application.order.output.ResellCustomOrderOutput;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.application.shared.mapper.AResellOrderMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AResellOrderMapper.class
        })
public interface APIResellCustomOrderMapper extends APIMapper<ResellCustomOrderInput, ResellCustomOrderOutput, AResellOrder, ResellCustomOrderRequest, ResellCustomOrderResponse> {

    @Mapping(target = ".", source = "resellOrder")
    AResellOrder toData(ResellCustomOrderOutput resellCustomOrderOutput);
}
