package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.request.CreateStandardOrderRequest;
import com.cplerings.core.api.order.response.CreateStandardOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.CreateStandardOrderInput;
import com.cplerings.core.application.order.output.CreateStandardOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateStandardOrderMapper extends APIMapper<CreateStandardOrderInput, CreateStandardOrderOutput, StandardOrderData, CreateStandardOrderRequest, CreateStandardOrderResponse> {
}
