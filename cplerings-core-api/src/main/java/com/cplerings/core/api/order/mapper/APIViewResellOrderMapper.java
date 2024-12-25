package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.order.request.ViewResellOrderRequest;
import com.cplerings.core.api.order.response.ViewResellOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.ViewResellOrderInput;
import com.cplerings.core.application.order.output.ViewResellOrderOutput;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.application.shared.mapper.AResellOrderMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
uses = {
        AResellOrderMapper.class,
})
public interface APIViewResellOrderMapper extends APIMapper<ViewResellOrderInput, ViewResellOrderOutput, AResellOrder, ViewResellOrderRequest, ViewResellOrderResponse> {

    @Mapping(target = ".", source = "resellOrder")
    AResellOrder toData(ViewResellOrderOutput viewResellOrderOutput);
}
