package com.cplerings.core.api.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.order.data.CustomOrderData;
import com.cplerings.core.api.order.request.ViewCustomOrderRequest;
import com.cplerings.core.api.order.request.ViewCustomOrdersRequest;
import com.cplerings.core.api.order.response.ViewCustomOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.order.input.ViewCustomOrderInput;
import com.cplerings.core.application.order.output.ViewCustomOrderOutput;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomOrderMapper extends APIMapper<ViewCustomOrderInput, ViewCustomOrderOutput, CustomOrderData, ViewCustomOrderRequest, ViewCustomOrderResponse> {
}
