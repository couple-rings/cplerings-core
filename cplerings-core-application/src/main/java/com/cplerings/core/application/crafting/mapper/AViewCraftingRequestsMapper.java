package com.cplerings.core.application.crafting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.crafting.datasource.result.CraftingRequests;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestsOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        }
)
public interface AViewCraftingRequestsMapper {

    @Mapping(target = "items", source = "craftingRequests")
    ViewCraftingRequestsOutput toOutput(CraftingRequests craftingRequests);
}
