package com.cplerings.core.api.transport.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.api.transport.request.GetTransportationOrderByCustomOrderRequest;
import com.cplerings.core.api.transport.response.GetTransportationOrderByCustomOrderResponse;
import com.cplerings.core.application.shared.entity.order.ATransportationOrder;
import com.cplerings.core.application.transport.input.GetTransportationOrderByCustomOrderInput;
import com.cplerings.core.application.transport.output.GetTransportationOrderByCustomOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIGetTransportationOrderByCustomOrderMapper extends APIMapper<GetTransportationOrderByCustomOrderInput, GetTransportationOrderByCustomOrderOutput, ATransportationOrder, GetTransportationOrderByCustomOrderRequest, GetTransportationOrderByCustomOrderResponse> {

    @Mapping(target = ".", source = "transportationOrder")
    ATransportationOrder toData(GetTransportationOrderByCustomOrderOutput getTransportationOrderByCustomOrderOutput);
}
