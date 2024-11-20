package com.cplerings.core.api.diamond.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.diamond.data.DiamondsData;
import com.cplerings.core.api.diamond.request.ViewDiamondsRequest;
import com.cplerings.core.api.diamond.response.ViewDiamondsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.diamond.input.ViewDiamondsInput;
import com.cplerings.core.application.diamond.output.ViewDiamondsOutput;
import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewDiamondsMapper extends APIPaginatedMapper<ViewDiamondsInput, ViewDiamondsOutput, DiamondsData, ADiamond, ViewDiamondsRequest, ViewDiamondsResponse> {
}
