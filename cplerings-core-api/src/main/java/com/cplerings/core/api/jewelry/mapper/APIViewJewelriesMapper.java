package com.cplerings.core.api.jewelry.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.jewelry.data.JewelriesData;
import com.cplerings.core.api.jewelry.request.ViewJewelriesRequest;
import com.cplerings.core.api.jewelry.response.ViewJewelriesResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.jewelry.input.ViewJewelriesInput;
import com.cplerings.core.application.jewelry.output.ViewJewelriesOutput;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewJewelriesMapper extends APIPaginatedMapper<ViewJewelriesInput, ViewJewelriesOutput, JewelriesData, AJewelry, ViewJewelriesRequest, ViewJewelriesResponse> {
}
