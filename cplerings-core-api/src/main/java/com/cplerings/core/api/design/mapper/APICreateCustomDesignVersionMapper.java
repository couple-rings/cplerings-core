package com.cplerings.core.api.design.mapper;

import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.design.request.CreateDesignVersionRequest;
import com.cplerings.core.api.design.response.CreateDesignVersionResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.CreateDesignVersionInput;
import com.cplerings.core.application.design.output.CreateDesignVersionOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateCustomDesignVersionMapper extends APIMapper<CreateDesignVersionInput, CreateDesignVersionOutput, DesignVersion, CreateDesignVersionRequest, CreateDesignVersionResponse> {
}
