package com.cplerings.core.api.crafting.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.crafting.request.CraftingRingRequest;
import com.cplerings.core.api.shared.mapper.APINoResponseMapper;
import com.cplerings.core.application.crafting.input.CraftingRingInput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICraftingRingMapper extends APINoResponseMapper<CraftingRingInput, CraftingRingRequest> {
}
