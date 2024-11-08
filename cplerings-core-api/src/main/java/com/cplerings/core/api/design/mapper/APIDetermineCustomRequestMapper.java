package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.CustomRequest;
import com.cplerings.core.api.design.data.CustomRequestData;
import com.cplerings.core.api.design.request.DetermineCustomRequestRequest;
import com.cplerings.core.api.design.response.DetermineCustomRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.DetermineCustomRequestInput;
import com.cplerings.core.application.design.output.DetermineCustomRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIDetermineCustomRequestMapper extends APIMapper<DetermineCustomRequestInput, DetermineCustomRequestOutput, CustomRequest, DetermineCustomRequestRequest, DetermineCustomRequestResponse> {
}
