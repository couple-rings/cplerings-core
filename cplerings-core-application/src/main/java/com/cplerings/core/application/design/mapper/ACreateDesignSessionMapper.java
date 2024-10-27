package com.cplerings.core.application.design.mapper;

import com.cplerings.core.application.design.output.CreateDesignSessionOutput;
import com.cplerings.core.application.shared.service.payment.PaymentRequest;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface ACreateDesignSessionMapper {

    CreateDesignSessionOutput toOutput(PaymentRequest paymentRequest);
}
