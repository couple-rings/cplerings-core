package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.design.ADiamondSpecification;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.diamond.DiamondSpecification;
import com.cplerings.core.domain.metal.MetalSpecification;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                MoneyMapper.class,
                AEnumMapper.class,
        }
)
public interface ADiamondMetalSpecificationMapper {

    ADiamondSpecification toDiamondSpecification(DiamondSpecification diamondSpecification);

    AMetalSpecification toMetalSpecification(MetalSpecification metalSpecification);
}
