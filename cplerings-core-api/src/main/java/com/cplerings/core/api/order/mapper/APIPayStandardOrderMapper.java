package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.order.data.StandardOrderPaymentLinkData;
import com.cplerings.core.api.order.request.PayStandardOrderRequest;
import com.cplerings.core.api.order.response.PayStandardOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.PayStandardOrderInput;
import com.cplerings.core.application.order.output.PayStandardOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIPayStandardOrderMapper extends APIMapper<PayStandardOrderInput, PayStandardOrderOutput, StandardOrderPaymentLinkData, PayStandardOrderRequest, PayStandardOrderResponse> {

}
