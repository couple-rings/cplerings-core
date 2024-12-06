package com.cplerings.core.application.payment.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.payment.output.GetPaymentOutput;
import com.cplerings.core.application.shared.mapper.ACraftingMapper;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.payment.Payment;

@Mapper(config = SpringMapperConfiguration.class,
uses = {
        WeightMapper.class,
        MoneyMapper.class,
        DesignSizeMapper.class,
        ACraftingMapper.class,
})
public interface AGetPaymentMapper {

    GetPaymentOutput toOutput(Payment payment);
}
