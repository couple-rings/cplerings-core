package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.design.request.UpdateDesignVersionStatusRequest;
import com.cplerings.core.api.design.response.UpdateDesignVersionStatusResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.UpdateDesignVersionStatusInput;
import com.cplerings.core.application.design.output.UpdateDesignVersionStatusOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIUpdateDesignVersionStatusMapper extends APIMapper<UpdateDesignVersionStatusInput, UpdateDesignVersionStatusOutput, DesignVersion, UpdateDesignVersionStatusRequest, UpdateDesignVersionStatusResponse> {
}
