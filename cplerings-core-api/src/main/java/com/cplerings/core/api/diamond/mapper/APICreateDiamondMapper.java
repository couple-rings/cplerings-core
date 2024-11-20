package com.cplerings.core.api.diamond.mapper;

import com.cplerings.core.api.diamond.request.CreateDiamondRequest;
import com.cplerings.core.api.diamond.response.CreateDiamondResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.diamond.input.CreateDiamondInput;
import com.cplerings.core.application.diamond.output.CreateDiamondOutput;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateDiamondMapper extends APIMapper<CreateDiamondInput, CreateDiamondOutput, ADiamond, CreateDiamondRequest, CreateDiamondResponse> {

    @Override
    @Mapping(target = ".", source = "diamond")
    ADiamond toData(CreateDiamondOutput output);
}
