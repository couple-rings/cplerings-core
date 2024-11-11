package com.cplerings.core.api.crafting.mapper;

import com.cplerings.core.api.crafting.data.CraftingRequestData;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestsRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsInput;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestsOutput;
import com.cplerings.core.application.shared.entity.crafting.ACraftingRequest;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCraftingRequestsMapper extends APIPaginatedMapper<ViewCraftingRequestsInput, ViewCraftingRequestsOutput, CraftingRequestData, ACraftingRequest, ViewCraftingRequestsRequest, ViewCraftingRequestsResponse> {

}
