package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewPaymentWithDateData;
import com.cplerings.core.api.dashboard.request.ViewPaymentWithDateRequest;
import com.cplerings.core.api.dashboard.response.ViewPaymentWithDateResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.dashboard.datasource.data.PaymentOrder;
import com.cplerings.core.application.dashboard.input.ViewPaymentWithDateInput;
import com.cplerings.core.application.dashboard.output.ViewPaymentWithDateOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewPaymentWithDateMapper extends APIPaginatedMapper<ViewPaymentWithDateInput, ViewPaymentWithDateOutput, ViewPaymentWithDateData, PaymentOrder, ViewPaymentWithDateRequest, ViewPaymentWithDateResponse> {
}
