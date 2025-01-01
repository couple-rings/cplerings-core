package com.cplerings.core.api.order.mapper;

import com.cplerings.core.api.order.data.PaymentInfosData;
import com.cplerings.core.api.order.request.ViewCustomOrderPaymentsRequest;
import com.cplerings.core.api.order.response.ViewCustomOrderPaymentsResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.ViewCustomOrderPaymentsInput;
import com.cplerings.core.application.order.output.ViewCustomOrderPaymentsOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomOrderPaymentsMapper extends APIMapper<ViewCustomOrderPaymentsInput, ViewCustomOrderPaymentsOutput, PaymentInfosData, ViewCustomOrderPaymentsRequest, ViewCustomOrderPaymentsResponse> {

}
