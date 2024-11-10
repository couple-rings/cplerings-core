package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.crafting.CraftingStage;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AEnumMapper.class,
                AImageMapper.class,
        }
)
public interface ACraftingMapper {

    @Mapping(target = "customOrderId", source = "customOrder.id")
    ACraftingStage toCraftingStage(CraftingStage craftingStage);
}
