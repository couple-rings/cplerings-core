package com.cplerings.core.api.crafting.mapper;

import com.cplerings.core.api.crafting.data.CraftingStagePaymentLinkData;
import com.cplerings.core.api.crafting.request.DepositCraftingStageRequest;
import com.cplerings.core.api.crafting.response.DepositCraftingStageResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.crafting.input.DepositCraftingStageInput;
import com.cplerings.core.application.crafting.output.DepositCraftingStageOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIDepositCraftingStageMapper extends APIMapper<DepositCraftingStageInput, DepositCraftingStageOutput, CraftingStagePaymentLinkData, DepositCraftingStageRequest, DepositCraftingStageResponse> {

}
