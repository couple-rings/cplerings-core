package com.cplerings.core.api.diamond.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.diamond.request.UpdateDiamondRequest;
import com.cplerings.core.api.diamond.response.UpdateDiamondResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.diamond.input.UpdateDiamondInput;
import com.cplerings.core.application.diamond.output.UpdateDiamondOutput;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.application.shared.mapper.ADiamondMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                ADiamondMapper.class,
        })
public interface APIUpdateDiamondMapper extends APIMapper<UpdateDiamondInput, UpdateDiamondOutput, ADiamond, UpdateDiamondRequest, UpdateDiamondResponse> {

        @Mapping(target = ".", source = "diamond")
        ADiamond toData(UpdateDiamondOutput updateDiamondOutput);
}
