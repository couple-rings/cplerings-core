package com.cplerings.core.application.shared.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.order.ATransportationOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.order.TransportationOrder;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                MoneyMapper.class,
                WeightMapper.class,
                DesignSizeMapper.class,
                ARingMapper.class,
        })
public interface ATransportationOrderMapper {

    ATransportationOrder toTransportationOrder(TransportationOrder transportationOrder);
}
