package com.cplerings.core.api.diamond.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.diamond.request.DisableDiamondRequest;
import com.cplerings.core.api.diamond.response.DisableDiamondResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.diamond.input.DisableDiamondInput;
import com.cplerings.core.application.diamond.output.DisableDiamondOutput;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.application.shared.mapper.ADiamondMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ADiamondMapper.class,
        })
public interface APIDisableDiamondMapper extends APIMapper<DisableDiamondInput, DisableDiamondOutput, ADiamond, DisableDiamondRequest, DisableDiamondResponse> {

    @Mapping(target = ".", source = "diamond")
    ADiamond toData(DisableDiamondOutput disableDiamondOutput);
}
