package com.cplerings.core.application.shared.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.jewelry.Jewelry;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ADesignMapper.class,
                WeightMapper.class,
                DesignSizeMapper.class,
        })
public interface AJewelryMapper {

    AJewelry toAJewelry(Jewelry jewelry);
}
