package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.design.mapper.AViewCustomDesignMapper;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.resell.ResellOrder;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AEnumMapper.class,
                MoneyMapper.class,
                WeightMapper.class,
                DesignSizeMapper.class,
                ARingMapper.class,
                AViewCustomDesignMapper.class,
                APaymentMapper.class,
                ADiamondMetalSpecificationMapper.class,
                ADocumentMapper.class,
                AImageMapper.class,
        })
public interface AResellOrderMapper {

    AResellOrder toAResellOrder(ResellOrder resellOrder);
}
