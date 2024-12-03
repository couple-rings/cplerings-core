package com.cplerings.core.application.jewelry.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.jewelry.datasource.result.Jewelries;
import com.cplerings.core.application.jewelry.output.ViewJewelriesOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                MoneyMapper.class,
                WeightMapper.class,
                DesignSizeMapper.class,
        })
public interface AViewJewelriesMapper {

    @Mapping(target = "items", source = "jewelries")
    ViewJewelriesOutput toOutput(Jewelries jewelries);
}
