package com.cplerings.core.api.jewelry.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.jewelry.data.JewelryCategoriesData;
import com.cplerings.core.api.jewelry.request.ViewJewelryCategoriesRequest;
import com.cplerings.core.api.jewelry.response.ViewJewelryCategoriesResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.jewelry.input.ViewJewelryCategoriesInput;
import com.cplerings.core.application.jewelry.output.ViewJewelryCategoriesOutput;
import com.cplerings.core.application.shared.entity.jewelry.AJewelryCategory;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewJewelryCategoriesMapper extends APIPaginatedMapper<ViewJewelryCategoriesInput, ViewJewelryCategoriesOutput, JewelryCategoriesData, AJewelryCategory, ViewJewelryCategoriesRequest, ViewJewelryCategoriesResponse> {
}
