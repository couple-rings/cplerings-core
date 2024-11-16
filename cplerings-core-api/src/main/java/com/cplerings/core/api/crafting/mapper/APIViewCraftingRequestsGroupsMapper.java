package com.cplerings.core.api.crafting.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.crafting.data.ViewCraftingRequestsGroupsData;
import com.cplerings.core.api.crafting.request.ViewCraftingRequestsGroupsRequest;
import com.cplerings.core.api.crafting.response.ViewCraftingRequestsGroupsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.crafting.input.ViewCraftingRequestsGroupsInput;
import com.cplerings.core.application.crafting.output.ViewCraftingRequestsGroupsOutput;
import com.cplerings.core.application.shared.entity.account.AAccountCraftingRequest;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCraftingRequestsGroupsMapper extends APIPaginatedMapper<ViewCraftingRequestsGroupsInput, ViewCraftingRequestsGroupsOutput, ViewCraftingRequestsGroupsData, AAccountCraftingRequest, ViewCraftingRequestsGroupsRequest, ViewCraftingRequestsGroupsResponse> {
}
