package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.request.ViewStandardOrderRequest;
import com.cplerings.core.api.order.response.ViewStandardOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.ViewStandardOrderInput;
import com.cplerings.core.application.order.output.ViewStandardOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewStandardOrderMapper extends APIMapper<ViewStandardOrderInput, ViewStandardOrderOutput, StandardOrderData, ViewStandardOrderRequest, ViewStandardOrderResponse> {
}
