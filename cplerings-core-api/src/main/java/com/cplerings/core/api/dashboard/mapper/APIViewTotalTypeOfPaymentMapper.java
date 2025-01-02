package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalTypeOfPaymentData;
import com.cplerings.core.api.dashboard.request.ViewTotalTypeOfPaymentRequest;
import com.cplerings.core.api.dashboard.response.ViewTotalTypeOfPaymentResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.dashboard.input.ViewTotalTypeOfPaymentInput;
import com.cplerings.core.application.dashboard.output.ViewTotalTypeOfPaymentOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalTypeOfPaymentMapper extends APIMapper<ViewTotalTypeOfPaymentInput, ViewTotalTypeOfPaymentOutput, ViewTotalTypeOfPaymentData, ViewTotalTypeOfPaymentRequest, ViewTotalTypeOfPaymentResponse> {
}
