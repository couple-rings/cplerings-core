package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.order.request.CancelStandardOrderRequest;
import com.cplerings.core.api.order.response.CancelStandardOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.CancelStandardOrderInput;
import com.cplerings.core.application.order.output.CancelStandardOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICancelStandardOrderMapper extends APIMapper<CancelStandardOrderInput, CancelStandardOrderOutput, StandardOrderData, CancelStandardOrderRequest, CancelStandardOrderResponse> {
}
