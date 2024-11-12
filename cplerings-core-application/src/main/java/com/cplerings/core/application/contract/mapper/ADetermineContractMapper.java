package com.cplerings.core.application.contract.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.contract.output.DetermineContractOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.contract.Contract;

@Mapper(config = SpringMapperConfiguration.class)
public interface ADetermineContractMapper {

    DetermineContractOutput toOutput(Contract contract);
}
