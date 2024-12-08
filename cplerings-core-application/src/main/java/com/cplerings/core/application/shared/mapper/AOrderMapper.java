package com.cplerings.core.application.shared.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.order.ACustomOrder;
import com.cplerings.core.application.shared.entity.order.ACustomOrderHistory;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AEnumMapper.class,
                ARingMapper.class,
                AAccountMapper.class,
                AContractMapper.class,
                MoneyMapper.class,
                ARefundMapper.class,
        }
)
public interface AOrderMapper {

    ACustomOrder toCustomOrder(CustomOrder customOrder);

    ACustomOrderHistory toCustomOrderHistory(CustomOrderHistory customOrderHistory);
}
