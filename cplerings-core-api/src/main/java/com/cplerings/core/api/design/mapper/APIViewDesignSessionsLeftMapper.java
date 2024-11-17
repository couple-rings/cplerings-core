package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.DesignSessionLeftData;
import com.cplerings.core.api.design.request.ViewDesignSessionsLeftRequest;
import com.cplerings.core.api.design.response.ViewDesignSessionsLeftResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.ViewDesignSessionsLeftInput;
import com.cplerings.core.application.design.output.ViewDesignSessionsLeftOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewDesignSessionsLeftMapper extends APIMapper<ViewDesignSessionsLeftInput, ViewDesignSessionsLeftOutput, DesignSessionLeftData, ViewDesignSessionsLeftRequest, ViewDesignSessionsLeftResponse> {
}
