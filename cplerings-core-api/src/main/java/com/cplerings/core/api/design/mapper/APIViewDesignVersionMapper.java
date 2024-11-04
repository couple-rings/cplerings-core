package com.cplerings.core.api.design.mapper;

import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.design.request.ViewDesignVersionRequest;
import com.cplerings.core.api.design.response.ViewDesignVersionResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.ViewDesignVersionInput;
import com.cplerings.core.application.design.output.ViewDesignVersionOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewDesignVersionMapper extends APIMapper<ViewDesignVersionInput, ViewDesignVersionOutput, DesignVersion, ViewDesignVersionRequest, ViewDesignVersionResponse> {

}
