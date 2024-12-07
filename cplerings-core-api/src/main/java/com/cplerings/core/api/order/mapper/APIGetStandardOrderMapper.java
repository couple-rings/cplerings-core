package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.request.GetStandardOrderByOrderNoRequest;
import com.cplerings.core.api.order.response.GetStandardOrderByOrderNoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.GetStandardOrderByOrderNoInput;
import com.cplerings.core.application.order.output.GetStandardOrderByOrderNoOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIGetStandardOrderMapper extends APIMapper<GetStandardOrderByOrderNoInput, GetStandardOrderByOrderNoOutput, StandardOrderData, GetStandardOrderByOrderNoRequest, GetStandardOrderByOrderNoResponse> {
}
