package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.api.design.request.ViewDesignRequest;
import com.cplerings.core.api.design.response.ViewDesignResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.ViewDesignInput;
import com.cplerings.core.application.design.output.ViewDesignOutput;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewDesignMapper extends APIMapper<ViewDesignInput, ViewDesignOutput, ADesign, ViewDesignRequest, ViewDesignResponse> {

    @Mapping(target = ".", source = "design")
    ADesign toData(ViewDesignOutput viewDesignOutput);
}
