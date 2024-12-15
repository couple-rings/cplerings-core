package com.cplerings.core.api.jewelry.mapper;

import com.cplerings.core.api.jewelry.request.ResellJewelryRequest;
import com.cplerings.core.api.jewelry.response.ResellJewelryResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.jewelry.input.ResellJewelryInput;
import com.cplerings.core.application.jewelry.output.ResellJewelryOutput;
import com.cplerings.core.application.shared.entity.order.AResellOrder;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIResellJewelryMapper extends APIMapper<ResellJewelryInput, ResellJewelryOutput, AResellOrder, ResellJewelryRequest, ResellJewelryResponse> {

    @Override
    @Mapping(target = ".", source = "resellOrder")
    AResellOrder toData(ResellJewelryOutput output);
}
