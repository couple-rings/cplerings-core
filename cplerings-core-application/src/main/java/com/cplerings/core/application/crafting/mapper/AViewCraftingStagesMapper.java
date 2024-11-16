package com.cplerings.core.application.crafting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.crafting.datasource.result.CraftingStages;
import com.cplerings.core.application.crafting.output.ViewCraftingStagesOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewCraftingStagesMapper {

    @Mapping(target = "items", source = "craftingStages")
    ViewCraftingStagesOutput toOutput(CraftingStages craftingStages);
}
