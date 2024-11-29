package com.cplerings.core.api.jewelry.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.jewelry.request.CreateJewelryRequest;
import com.cplerings.core.api.jewelry.response.CreateJewelryResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.jewelry.input.CreateJewelryInput;
import com.cplerings.core.application.jewelry.output.CreateJewelryOutput;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.application.shared.mapper.AJewelryMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AJewelryMapper.class
        })
public interface APICreateJewelryMapper extends APIMapper<CreateJewelryInput, CreateJewelryOutput, AJewelry, CreateJewelryRequest, CreateJewelryResponse> {

    @Mapping(target = ".", source = "jewelry")
    AJewelry toData(CreateJewelryOutput createJewelryOutput);
}
