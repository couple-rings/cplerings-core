package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.design.output.CreateDesignSessionOutput;
import com.cplerings.core.application.shared.service.payment.PaymentRequest;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface ACreateDesignSessionMapper {

    @Mapping(target = "paymentId", source = "payment.id")
    CreateDesignSessionOutput toOutput(PaymentRequest paymentRequest);
}
