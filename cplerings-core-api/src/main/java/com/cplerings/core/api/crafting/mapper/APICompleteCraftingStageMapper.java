package com.cplerings.core.api.crafting.mapper;

import com.cplerings.core.api.crafting.request.CompleteCraftingStageRequest;
import com.cplerings.core.api.crafting.response.CompleteCraftingStageResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.crafting.input.CompleteCraftingStageInput;
import com.cplerings.core.application.crafting.output.CompleteCraftingStageOutput;
import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICompleteCraftingStageMapper extends APIMapper<CompleteCraftingStageInput, CompleteCraftingStageOutput, ACraftingStage, CompleteCraftingStageRequest, CompleteCraftingStageResponse> {

}
