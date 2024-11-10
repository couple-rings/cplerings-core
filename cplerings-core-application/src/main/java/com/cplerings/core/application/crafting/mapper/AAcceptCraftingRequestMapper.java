package com.cplerings.core.application.crafting.mapper;

import com.cplerings.core.application.crafting.output.AcceptCraftingRequestOutput;
import com.cplerings.core.application.shared.mapper.DesignSizeMapper;
import com.cplerings.core.application.shared.mapper.MoneyMapper;
import com.cplerings.core.application.shared.mapper.WeightMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.order.CustomOrder;

import org.mapstruct.Mapper;

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
