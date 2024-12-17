package com.cplerings.core.api.jewelry.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.jewelry.request.UpdateJewelryRequest;
import com.cplerings.core.api.jewelry.response.UpdateJewelryResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.jewelry.input.UpdateJewelryInput;
import com.cplerings.core.application.jewelry.output.UpdateJewelryOutput;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.application.shared.mapper.AJewelryMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AJewelryMapper.class,
        })
public interface APIUpdateJewelryMapper extends APIMapper<UpdateJewelryInput, UpdateJewelryOutput, AJewelry, UpdateJewelryRequest, UpdateJewelryResponse> {

    @Mapping(target = ".", source = "jewelry")
    AJewelry toData(UpdateJewelryOutput output);
}
