package com.cplerings.core.application.shared.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.resell.ResellOrder;

@Mapper(config = SpringMapperConfiguration.class,
uses = {
        MoneyMapper.class,
        WeightMapper.class,
        DesignSizeMapper.class
})
public interface AResellOrderMapper {

    AResellOrder toAResellOrder(ResellOrder resellOrder);
}
