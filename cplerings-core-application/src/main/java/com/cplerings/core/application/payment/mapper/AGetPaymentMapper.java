package com.cplerings.core.application.payment.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.payment.output.GetPaymentOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.payment.Payment;

@Mapper(config = SpringMapperConfiguration.class)
public interface AGetPaymentMapper {

    GetPaymentOutput toOutput(Payment payment);
}
