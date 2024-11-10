package com.cplerings.core.api.diamond.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.diamond.data.DiamondSpecification;
import com.cplerings.core.api.diamond.request.ViewDiamondSpecificationRequest;
import com.cplerings.core.api.diamond.response.ViewDiamondSpecificationResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;
import com.cplerings.core.application.diamond.output.ViewDiamondSpecificationOutput;
import com.cplerings.core.application.shared.entity.design.ADiamondSpecification;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewDiamondSpecificationMapper extends APIPaginatedMapper<ViewDiamondSpecificationInput, ViewDiamondSpecificationOutput, DiamondSpecification, ADiamondSpecification, ViewDiamondSpecificationRequest, ViewDiamondSpecificationResponse> {
}
