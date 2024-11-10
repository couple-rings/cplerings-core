package com.cplerings.core.application.metal.mapper;

import com.cplerings.core.application.metal.datasource.result.MetalSpecifications;
import com.cplerings.core.application.metal.output.ViewMetalSpecificationOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        })
public interface AViewMetalSpecificationMapper {

    @Mapping(target = "items", source = "metalSpecifications")
    ViewMetalSpecificationOutput toOutput(MetalSpecifications metalSpecifications);
}
