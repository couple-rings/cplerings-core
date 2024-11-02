package com.cplerings.core.application.craftingrequest.mapper;

import com.cplerings.core.application.craftingrequest.output.CreateCraftingRequestOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.crafting.CraftingRequest;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        }
)
public interface ACreateCraftingRequestMapper {

    CreateCraftingRequestOutput toOutput(CraftingRequest craftingRequest);
}
