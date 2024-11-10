package com.cplerings.core.application.diamond.mapper;

import com.cplerings.core.application.diamond.datasource.result.DiamondSpecifications;
import com.cplerings.core.application.diamond.output.ViewDiamondSpecificationOutput;
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
public interface AViewDiamondSpecificationMapper {

    @Mapping(target = "items", source = "diamondSpecifications")
    ViewDiamondSpecificationOutput toOutput(DiamondSpecifications diamondSpecifications);
}
