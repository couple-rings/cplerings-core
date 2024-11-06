package com.cplerings.core.api.craftingrequest.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.craftingrequest.data.CustomOrderCraftingRequestData;
import com.cplerings.core.api.craftingrequest.request.AcceptCraftingRequestRequest;
import com.cplerings.core.api.craftingrequest.response.AcceptCraftingRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.craftingrequest.input.AcceptCraftingRequestInput;
import com.cplerings.core.application.craftingrequest.output.AcceptCraftingRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIAcceptCraftingRequestMapper extends APIMapper<AcceptCraftingRequestInput, AcceptCraftingRequestOutput, CustomOrderCraftingRequestData, AcceptCraftingRequestRequest, AcceptCraftingRequestResponse> {
}
