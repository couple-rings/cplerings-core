package com.cplerings.core.application.crafting.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.crafting.output.ViewCraftingRequestOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.crafting.CraftingRequest;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        }
)
public interface AViewCraftingRequestMapper {

    ViewCraftingRequestOutput toOutput(CraftingRequest craftingRequest);
}
