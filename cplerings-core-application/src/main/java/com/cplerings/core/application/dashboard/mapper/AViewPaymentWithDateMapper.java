package com.cplerings.core.application.dashboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.dashboard.datasource.data.PaymentOrders;
import com.cplerings.core.application.dashboard.output.ViewPaymentWithDateOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewPaymentWithDateMapper {

    @Mapping(target = "items", source = "payments")
    ViewPaymentWithDateOutput toOutput(PaymentOrders payments);
}
