package com.cplerings.core.api.jewelry.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.jewelry.request.GetJewelryByProductNoRequest;
import com.cplerings.core.api.jewelry.response.GetJewelryByProductNoResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.jewelry.input.GetJewelryByProductNoInput;
import com.cplerings.core.application.jewelry.output.GetJewelryByProductNoOutput;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.application.shared.mapper.AJewelryMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AJewelryMapper.class,
        })
public interface APIGetJewelryByProductNoMapper extends APIMapper<GetJewelryByProductNoInput, GetJewelryByProductNoOutput, AJewelry, GetJewelryByProductNoRequest, GetJewelryByProductNoResponse> {

        @Mapping(target = ".", source = "jewelry")
        AJewelry toData(GetJewelryByProductNoOutput output);
}
