package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.CustomRequest;
import com.cplerings.core.api.design.request.CancelCustomRequestRequest;
import com.cplerings.core.api.design.response.CancelCustomRequestResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.CancelCustomRequestInput;
import com.cplerings.core.application.design.output.CancelCustomRequestOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICancelCustomRequestMapper extends APIMapper<CancelCustomRequestInput, CancelCustomRequestOutput, CustomRequest, CancelCustomRequestRequest, CancelCustomRequestResponse> {
}
