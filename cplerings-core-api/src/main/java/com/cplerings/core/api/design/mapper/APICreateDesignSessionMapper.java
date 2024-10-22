package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.DesignSession;
import com.cplerings.core.api.design.request.CreateDesignSessionRequest;
import com.cplerings.core.api.design.response.CreateDesignSessionResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.CreateDesignSessionInput;
import com.cplerings.core.application.design.output.CreateDesignSessionOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateDesignSessionMapper extends APIMapper<CreateDesignSessionInput, CreateDesignSessionOutput, DesignSession, CreateDesignSessionRequest, CreateDesignSessionResponse> {
}
