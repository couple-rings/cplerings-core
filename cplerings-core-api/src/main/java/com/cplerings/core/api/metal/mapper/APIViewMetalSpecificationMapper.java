package com.cplerings.core.api.metal.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.metal.data.MetalSpecification;
import com.cplerings.core.api.metal.request.ViewMetalSpecificationRequest;
import com.cplerings.core.api.metal.response.ViewMetalSpecificationResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.metal.input.ViewMetalSpecificationInput;
import com.cplerings.core.application.metal.output.ViewMetalSpecificationOutput;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewMetalSpecificationMapper extends APIPaginatedMapper<ViewMetalSpecificationInput, ViewMetalSpecificationOutput, MetalSpecification, AMetalSpecification, ViewMetalSpecificationRequest, ViewMetalSpecificationResponse> {
}
