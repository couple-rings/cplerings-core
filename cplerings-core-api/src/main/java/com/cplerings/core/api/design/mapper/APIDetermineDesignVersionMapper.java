package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.design.request.DetermineDesignVersionRequest;
import com.cplerings.core.api.design.response.DetermineDesignVersionResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.DetermineDesignVersionInput;
import com.cplerings.core.application.design.output.DetermineDesignVersionOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIDetermineDesignVersionMapper extends APIMapper<DetermineDesignVersionInput, DetermineDesignVersionOutput, DesignVersion, DetermineDesignVersionRequest, DetermineDesignVersionResponse> {
}
