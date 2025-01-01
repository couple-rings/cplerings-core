package com.cplerings.core.application.crafting.mapper;

import com.cplerings.core.application.crafting.datasource.result.CraftingStages;
import com.cplerings.core.application.crafting.output.ViewCraftingStagesOutput;
import com.cplerings.core.application.shared.mapper.ACraftingMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                ACraftingMapper.class,
        }
)
public interface AViewCraftingStagesMapper {

    @Mapping(target = "items", source = "craftingStages")
    ViewCraftingStagesOutput toOutput(CraftingStages craftingStages);
}
