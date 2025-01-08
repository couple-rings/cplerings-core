package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalPaymentPerOrderData;
import com.cplerings.core.api.dashboard.request.ViewTotalPaymentPerOrderRequest;
import com.cplerings.core.api.dashboard.response.ViewTotalPaymentPerOrderResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.dashboard.input.ViewTotalPaymentPerOrderInput;
import com.cplerings.core.application.dashboard.output.ViewTotalPaymentPerOrderOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalPaymentPerOrderMapper extends APIMapper<ViewTotalPaymentPerOrderInput, ViewTotalPaymentPerOrderOutput, ViewTotalPaymentPerOrderData, ViewTotalPaymentPerOrderRequest, ViewTotalPaymentPerOrderResponse> {
}
