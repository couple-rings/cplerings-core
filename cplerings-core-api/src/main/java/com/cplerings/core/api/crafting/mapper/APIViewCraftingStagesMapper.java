package com.cplerings.core.api.crafting.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.crafting.data.CraftingStagesData;
import com.cplerings.core.api.crafting.request.ViewCraftingStagesRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingStagesResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.crafting.input.ViewCraftingStagesInput;
import com.cplerings.core.application.crafting.output.ViewCraftingStagesOutput;
import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCraftingStagesMapper extends APIPaginatedMapper<ViewCraftingStagesInput, ViewCraftingStagesOutput, CraftingStagesData, ACraftingStage, ViewCraftingStagesRequest, ViewCraftingStagesResponse> {
}
