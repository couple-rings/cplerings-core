package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.DesignsData;
import com.cplerings.core.api.design.request.ViewDesignsRequest;
import com.cplerings.core.api.design.response.ViewDesignsResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.design.input.ViewDesignsInput;
import com.cplerings.core.application.design.output.ViewDesignsOutput;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewDesignsMapper extends APIPaginatedMapper<ViewDesignsInput, ViewDesignsOutput, DesignsData, ADesign, ViewDesignsRequest, ViewDesignsResponse> {
}
