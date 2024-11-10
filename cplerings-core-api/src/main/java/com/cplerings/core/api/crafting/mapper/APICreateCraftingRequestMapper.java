package com.cplerings.core.api.crafting.mapper;

import com.cplerings.core.api.crafting.data.CraftingRequest;
import com.cplerings.core.api.crafting.request.CreateCraftingRequestRequest;
import com.cplerings.core.api.crafting.response.CreateCraftingRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.crafting.input.CreateCraftingRequestInput;
import com.cplerings.core.application.crafting.output.CreateCraftingRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateCraftingRequestMapper extends APIMapper<CreateCraftingRequestInput, CreateCraftingRequestOutput, CraftingRequest, CreateCraftingRequestRequest, CreateCraftingRequestResponse> {
}
