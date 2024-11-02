package com.cplerings.core.api.craftingrequest.mapper;

import com.cplerings.core.api.craftingrequest.data.CraftingRequest;
import com.cplerings.core.api.craftingrequest.request.CreateCraftingRequestRequest;
import com.cplerings.core.api.craftingrequest.response.CreateCraftingRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.craftingrequest.input.CreateCraftingRequestInput;
import com.cplerings.core.application.craftingrequest.output.CreateCraftingRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateCraftingRequestMapper extends APIMapper<CreateCraftingRequestInput, CreateCraftingRequestOutput, CraftingRequest, CreateCraftingRequestRequest, CreateCraftingRequestResponse> {
}
