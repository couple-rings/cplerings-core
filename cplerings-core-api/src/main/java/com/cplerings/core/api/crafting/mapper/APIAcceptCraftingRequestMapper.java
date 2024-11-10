package com.cplerings.core.api.crafting.mapper;

import com.cplerings.core.api.crafting.data.CustomOrderCraftingRequestData;
import com.cplerings.core.api.crafting.request.AcceptCraftingRequestRequest;
import com.cplerings.core.api.crafting.response.AcceptCraftingRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.crafting.input.AcceptCraftingRequestInput;
import com.cplerings.core.application.crafting.output.AcceptCraftingRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIAcceptCraftingRequestMapper extends APIMapper<AcceptCraftingRequestInput, AcceptCraftingRequestOutput, CustomOrderCraftingRequestData, AcceptCraftingRequestRequest, AcceptCraftingRequestResponse> {
}
