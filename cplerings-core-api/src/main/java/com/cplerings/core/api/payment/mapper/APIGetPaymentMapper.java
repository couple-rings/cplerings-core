package com.cplerings.core.api.payment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.payment.request.GetPaymentRequest;
import com.cplerings.core.api.payment.response.GetPaymentResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.payment.input.GetPaymentInput;
import com.cplerings.core.application.payment.output.GetPaymentOutput;
import com.cplerings.core.application.shared.entity.payment.APayment;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIGetPaymentMapper extends APIMapper<GetPaymentInput, GetPaymentOutput, APayment, GetPaymentRequest, GetPaymentResponse> {

    @Mapping(target = ".", source = "payment")
    APayment toData(GetPaymentOutput output);
}
