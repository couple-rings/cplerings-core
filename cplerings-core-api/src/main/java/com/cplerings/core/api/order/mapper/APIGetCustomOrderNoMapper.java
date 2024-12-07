package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.order.request.GetCustomOrderByOrderNoRequest;
import com.cplerings.core.api.order.response.GetCustomOrderByOrderNoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.GetCustomOrderByOrderNoInput;
import com.cplerings.core.application.order.output.GetCustomOrderByOrderNoOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIGetCustomOrderNoMapper extends APIMapper<GetCustomOrderByOrderNoInput, GetCustomOrderByOrderNoOutput, CustomOrderData, GetCustomOrderByOrderNoRequest, GetCustomOrderByOrderNoResponse> {
}
