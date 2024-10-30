package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.CustomDesign;
import com.cplerings.core.api.design.request.CreateCustomDesignRequest;
import com.cplerings.core.api.design.response.CreateCustomDesignResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.CreateCustomDesignInput;
import com.cplerings.core.application.design.output.CreateCustomDesignOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateCustomDesignMapper extends APIMapper<CreateCustomDesignInput, CreateCustomDesignOutput, CustomDesign, CreateCustomDesignRequest, CreateCustomDesignResponse> {
}
