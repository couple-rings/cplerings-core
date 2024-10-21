package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.payment.APaymentStatus;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.payment.PaymentStatus;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APaymentStatusMapper {

    APaymentStatus toStatus(PaymentStatus paymentStatus);
}
