package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.order.request.AssignJewelerToCustomOrderRequest;
import com.cplerings.core.api.order.response.AssignJewelerToCustomOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.AssignJewelerToCustomOrderInput;
import com.cplerings.core.application.order.output.AssignJewelerToCustomOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIAssignJewelerToCustomOrderMapper extends APIMapper<AssignJewelerToCustomOrderInput, AssignJewelerToCustomOrderOutput, CustomOrderData, AssignJewelerToCustomOrderRequest, AssignJewelerToCustomOrderResponse> {
}
