package com.cplerings.core.application.jewelry.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.jewelry.datasource.result.JewelryCategories;
import com.cplerings.core.application.jewelry.output.ViewJewelryCategoriesOutput;
import com.cplerings.core.application.shared.mapper.ADesignMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ADesignMapper.class,

        })
public interface AViewJewelryCategoriesMapper {

    @Mapping(target = "items", source = "jewelryCategories")
    ViewJewelryCategoriesOutput toOutput(JewelryCategories jewelryCategories);
}
