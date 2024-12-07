package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.request.CompleteOrderRequest;
import com.cplerings.core.api.shared.mapper.APINoResponseMapper;
import com.cplerings.core.application.order.input.CompleteOrderInput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICompleteOrderMapper extends APINoResponseMapper<CompleteOrderInput, CompleteOrderRequest> {
}
