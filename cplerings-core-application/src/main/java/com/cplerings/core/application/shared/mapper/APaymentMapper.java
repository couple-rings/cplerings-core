package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.payment.APaymentInfo;
import com.cplerings.core.application.shared.entity.payment.AVNPayTransaction;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.transaction.VNPayTransaction;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AEnumMapper.class,
                MoneyMapper.class,
        }
)
public interface APaymentMapper {

    APaymentInfo toPaymentInfo(Payment payment);

    AVNPayTransaction toVNPayTransaction(VNPayTransaction vnPayTransaction);
}
