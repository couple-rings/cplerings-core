package com.cplerings.core.api.order.mapper;

import com.cplerings.core.api.order.request.CancelCustomOrderRequest;
import com.cplerings.core.api.shared.mapper.APINoResponseMapper;
import com.cplerings.core.application.order.input.CancelCustomOrderInput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICancelCustomOrderMapper extends APINoResponseMapper<CancelCustomOrderInput, CancelCustomOrderRequest> {

}
