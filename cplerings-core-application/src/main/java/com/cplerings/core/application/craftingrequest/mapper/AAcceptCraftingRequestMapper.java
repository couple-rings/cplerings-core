package com.cplerings.core.application.craftingrequest.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.craftingrequest.output.AcceptCraftingRequestOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.order.CustomOrder;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                WeightMapper.class,
                DesignSizeMapper.class,
                MoneyMapper.class
        }
)
public interface AAcceptCraftingRequestMapper {

    AcceptCraftingRequestOutput toOutput(CustomOrder customOrder, CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest);
}
