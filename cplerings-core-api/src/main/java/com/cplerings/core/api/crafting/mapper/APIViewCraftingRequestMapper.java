package com.cplerings.core.api.crafting.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.crafting.data.CraftingRequest;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestInput;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCraftingRequestMapper extends APIMapper<ViewCraftingRequestInput, ViewCraftingRequestOutput, CraftingRequest, ViewCraftingRequestRequest, ViewCraftingRequestResponse> {
}
