package com.cplerings.core.api.contract.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.contract.data.Contract;
import com.cplerings.core.api.contract.request.DetermineContractRequest;
import com.cplerings.core.api.contract.response.DetermineContractResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.contract.input.DetermineContractInput;
import com.cplerings.core.application.contract.output.DetermineContractOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIDetermineContractMapper extends APIMapper<DetermineContractInput, DetermineContractOutput, Contract, DetermineContractRequest, DetermineContractResponse> {
}
